jQuery.noConflict();

$(function () {
    var driverInfo = {};

    getPersonalInfo();

    function getPersonalInfo() {
        var url = getContextPath() + 'index/getIndexInfo';
        $.getJSON(url, function (data) {
            if (data.success) {
                if (!data.driverInfo.companyId) {
                    $.alert('请先绑定您的所属公司', function () {
                        var url = getContextPath() + 'frontend/chooseCompany';
                        $.router.load(url);
                        return
                    });

                }
                $('#company').text(data.driverInfo.companyName);
                $('#myself').text(data.driverInfo.name);
                if (data.vehicleInfo) {
                    $('#truck').text(data.vehicleInfo.licensePlate);
                    make2DCode(data.vehicleInfo.id, data.driverInfo.id);
                } else {
                    $('#qrcode').html('<p class="tip">请选择本次作业车辆！</p>');
                }
                driverInfo = data.driverInfo;
            } else {
                $('#myself').text('未绑定司机信息');
                $('#company').text('未绑定承运关系');
                $('#truck').text('无可用车辆')
                $('#qrcode').html('<p class="tip">请先完善您的个人信息！</p>');
            }
        });
    }

    /**
     * 跳转电子货运单单页面
     */
    $('#e-bill').on('click', function () {
        var url = getContextPath() + 'frontend/waybill';
        $.router.load(url);
    });

    /**
     * 跳转个人信息
     */
    $('#gomyself').on('click', function () {
        var url = getContextPath() + 'frontend/personal';
        $.router.load(url);
    });

    /**
     * 跳转订单页面
     */
    $('#dispatch-bill').on('click', function () {
        //var url = getContextPath() + 'tbDispatchBill/list';
        //$.router.load(url);
        location.href = getContextPath() + 'tbDispatchBill/list';
    });

    /**
     * 扫描二维码
     */
    $('.scan-btn').on('click', function () {
        mclient.scanQRcode();
    });

    $(document).on('click', '#choose-vehicle', function () {
        if (!driverInfo) {
            $.toast('请完善个人信息！');
            return;
        }

        var url = getContextPath() + "driverVehicle/getDriverVehicleInfo?companyId=" + driverInfo.companyId + "&driverId=" + driverInfo.id;
        $.getJSON(url, function (data) {
            if (data.success) {
                var vehicleInfolist = data.vehicleInfolist;
                $("#content").html("");

                if (vehicleInfolist != null && vehicleInfolist != '') {
                    for (var i = 0; i < vehicleInfolist.length; i++) {
                        $("#content").append('<a href="#" class="aui-info-box-item choice" style="position: relative;">' +
                            '<div class="aui-img-sms">' +
                            '<img src="../resources/images/truc1.png" alt="">' +
                            '</div>' +
                            '<div class="aui-flex">' +
                            '<h3 class="license" data-id="' + vehicleInfolist[i].id + '">' + vehicleInfolist[i].licensePlate + '</h3>' +
                            '</div>' +
                            '</a>');
                    }
                }
            }
        });
        $.popup('#choose');
    });

    /**
     * 选择车辆
     */
    $(document).on('click', '.choice', function () {
        var obj = $(this).find('.license');
        var vehicleId = obj.attr('data-id');
        $('#truck').text(obj.text());
        make2DCode(vehicleId, driverInfo.id);

        $.closeModal('#choose');
    });

    //生成二维码
    function make2DCode(vehicleId, driverId) {
        var info = {
            vehicleId: vehicleId,
            driverId: driverId
        }

        /* var url = getContextPath() + "driverVehicle/getQRCodeInfo"

        $.getJSON(url, info, function (data) {
            if (data.success) {
                info.name = data.driverInfo.name;
                info.licensePlate = data.vehicleInfo.licensePlate;
                info.IDCard = data.driverInfo.idCord;
                info.licenseNumber = data.driverInfo.licenseNumber;
                info.maxPayload = data.vehicleInfo.maxPayload;

                // 数据加密，如果有需要，可以解除注释，并把infostr作为提交数据
                //var infostr = JSON.stringify(info);
                //infostr = btoa(encodeURI(infostr));


            } else {
                $.alert(data.errMsg);
            }
        }); */

        $("#qrcode").html(""); //清空二维码显示区域
        jQuery('#qrcode').qrcode({
            render: "canvas",
            width: "100",
            height: "100",
            correctLevel: 0,
            text: utf16to8(JSON.stringify(info))
        });
    }

    //支持中文
    function utf16to8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }
});

function getScanMessage(arg) {
    $.showPreloader('数据处理中，请稍后...');

    $.ajax({
        url: getContextPath() + "dealMsg/checkCache",
        data: {
            key: arg
        },
        dataType: "TEXT",
        type: "GET",
        success: function (data) {
            data = JSON.parse(data);
            if (data.success) {
                // 上传当前车牌号及司机信息到缓存中
                uploadInfo(data.realKey);
                $("#no-state").hide();
                $("#empty-weight").css("display", "flex");
            } else {
                $.hidePreloader();
                $.toast(data.errMsg);
            }
        },
        complete: function () {
            $.hidePreloader();
        }
    });
}

/**
 * 把数据上传到缓存中
 * @returns
 */
function uploadInfo(realKey) {
    var value = JSON.stringify({
        licensePlate: licensePlate,
        dispatchingId: dispatchingId ? dispatchingId : "",
    });

    $.ajax({
        url: getContextPath() + "dealMsg/setdata",
        data: {
            key: realKey,
            value: value,
        },
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            data = JSON.parse(data);
            if (data.success) {
                var date = new Date().Format("yyyy-MM-dd hh:mm");
                if (isSecond) {
                    $("#second-time").text(date);
                    $("#fillin-weight").css("display", "flex");
                } else {
                    $("#first-time").text(date);
                    $("#empty-weight").css("display", "flex");
                }

                $.hidePreloader();

                $.toast("扫描成功，运营人员正在处理！");
            } else {
                $.hidePreloader();

                $.alert("信息上传失败！请重新扫描二维码");
            }
        },
        complete: function () {
            $.hidePreloader();
        }
    });
}