jQuery.noConflict();

$(function () {
    var id = getQueryString('id');
    var url = getContextPath() + 'poundrecord/getWayBill?id=' + id;

    getDetail();

    function getDetail() {
        $.getJSON(url, function (data) {
            if (data.success) {
                make2DCode(data.result.id);

                for (item in data.result) {
                    if (item.indexOf('Time') > -1) {
                        var time = Number(data.result[item]);
                        var date = new Date(time).Format('yyyy-MM-dd hh:mm');
                        $('#' + item).text(date);
                    } else {
                        $('#' + item).text(data.result[item]);
                    }
                }

                var first = data.result.firstWeight;
                var second = data.result.secondWeight;
                var netWeight = (Number(second) - Number(first)).toFixed(3);
                $('#netWeight').text(netWeight);
            }
        });
    }

    /**
     * 返回
     */
    $('.back').on('click', function () {
        var url = getContextPath() + 'frontend/waybill';
        $.router.load(url);
    });

    //生成二维码
    function make2DCode(id) {
        var url = 'http://119.3.185.170:8888/dcs/verify/getRecord?id=' + id;

        $("#qrcode").html(""); //清空二维码
        jQuery('#qrcode').qrcode({
            render: "canvas",
            width: "100",
            height: "100",
            correctLevel: 0,
            text: url,
        });
    }
});