// 加载标识
var loading = false;
// 页码
var pageNum = 1;
// 每次加载添加多少条目（页大小）
var pageSize = 15;
// 最多可加载的条目
var maxItems = 100;
// 通过无限加载加载出的条目（即：不计算轮询出的条目数）
var items = 0;
// 获取数据路径
var url = getContextPath() + "poundrecord/waybilllist";
// 排序方式，默认由近及远
var sort = 'DESC';
// 时间筛选：开始时间和结束时间
var start = '';
var end = '';

$(function () {
    addItems(pageSize, pageNum);

    /**
     * 跳转个人信息
     */
    $('#gomyself').on('click', function () {
        var url = getContextPath() + 'frontend/personal';
        $.router.load(url)
        //location.href = getContextPath() + 'frontend/personal';
    });

    /**
     * 跳转订单页面
     */
    $('#dispatch-bill').on('click', function () {
        location.href = getContextPath() + 'tbDispatchBill/list';
    });

    /**
     * 添加条目（上滑加载）
     * @param {number} pageSize 页大小
     * @param {number} pageNum 页数
     */
    function addItems(pageSize, pageIndex) {
        var map = {
            pageIndex: pageIndex,
            pageSize: pageSize,
            licensePlate: $("#licensePlate").val(),
            sort: sort,
        }

        // 如果开始时间和结束时间都不为空，那么就作为筛选条件
        if (start && end) {
            map.timeEnd = end;
            map.timeBegin = start;
        }

        $.getJSON(url, map, function (data) {
            if (data.success) {
                maxItems = data.count;
                if (maxItems == 0) {
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                    var nodata = "<div class='nodata' style='text-align:center;padding-top:150px;'><img style='width:150px;display:inline;' src='" + getContextPath() + "resources/images/nodata.png'></div>";
                    $('.container').html(nodata);
                } else {
                    $('.nodata').remove();
                }
                // 生成新条目的HTML
                var html = '';
                if (data.list && data.list.length > 0) {
                    for (var i = 0; i < data.list.length; i++) {
                        // 时间格式化处理
                        var firstTime = "";
                        if (data.list[i].firstTime) {
                            firstTime = new Date(data.list[i].firstTime).Format("yyyy-MM-dd hh:mm:ss");
                        }

                        var licensePlate = data.list[i].licensePlate ? data.list[i].licensePlate : "未知"

                        html += '<a href="javascript:;" class="aui-flex b-line showbill" data-id="' + data.list[i].id + '">' +
                            '<div class="aui-flex-box">' +
                            '<p>车牌号：' + licensePlate + '</p>' +
                            '<p class="p1">用车时间：' + firstTime + '</p>' +
                            '<p class="p1">出发地点：' + (data.list[i].companyName ? data.list[i].companyName : "") + '</p>' +
                            '</div>' +
                            '<div class="aui-arrow"></div>' +
                            '</a>';
                    }
                }

                // 添加新条目
                $('.container').append(html);

                // 更新最后加载的页码
                pageNum++;
                items += data.list.length;

                // 如果第一次加载的数量达到了能获取到的最大数
                // 那么就移除加载标志
                if (items >= maxItems) {
                    // 加载完毕，则注销无限加载事件，以防不必要的加载
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                }

                $.refreshScroller();
            }

        });
    }

    // 无限滚动
    $(document).on("pageInit", ".page", function (e, id, page) {
        var timer = false;
        $(page).on('infinite', function () {

            // 如果正在加载，则退出
            if (timer) {
                clearTimeout(timer);
            }

            // 模拟1s的加载过程
            timer = setTimeout(function () {

                addItems(pageSize, pageNum);

            }, 1000);
        });

    });

    // 添加'refresh'监听器
    $(document).on('refresh', '.pull-to-refresh-content', function (e) {
        // 模拟1s的加载过程
        setTimeout(function () {
            // 删除原有列表，重置初始化数据
            $(".showbill").remove();
            items = 0;
            if ($(".infinite-scroll-preloader").length == 0) {
                $(".infinite-scroll").append('<div class="infinite-scroll-preloader">' +
                    '<div class="preloader"> </div>' +
                    '</div>')
            }
            pageNum = 1;
            addItems(pageSize, pageNum);
            // 加载完毕需要重置
            $.pullToRefreshDone('.pull-to-refresh-content');
        }, 1000);
    });

    $.init();

    /**
     * 跳转作业界面
     */
    $('#gowork').on('click', function () {
        var url = getContextPath() + 'frontend/index';
        $.router.load(url)
        //location.href = url;
    });

    /**
     * 展示电子单详细
     */
    $(document).on('click', '.showbill', function () {
        var id = $(this).attr('data-id');
        var url = getContextPath() + 'frontend/billList?id=' + id;
        $.router.load(url)
        //location.href = url;
    });

    $('#time').on('change', function () {
        var value = $('#time').val();
        var now = new Date();

        switch (value) {
            case "today":
                start = now.Format("yyyy-MM-dd 00:00");
                end = now.Format("yyyy-MM-dd 23:59");
                break;
            case "week":
                start = new Date(now.getFullYear(), now.getMonth(), now.getDate() - now.getDay()).Format("yyyy-MM-dd 00:00");
                end = new Date(now.getFullYear(), now.getMonth(), now.getDate() + (6 - now.getDay())).Format("yyyy-MM-dd 23:59");
                break;
            case "month":
                start = new Date(now.setDate(1)).Format("yyyy-MM-dd 00:00");
                end = new Date(now.setDate(31)).Format("yyyy-MM-dd 23:59");
                break;
            case "all":
            default:
                start = "";
                end = "";

        }

        search();
    });

    $('#licensePlate').on('keyup', function () {
        search();
    });

    function search() {
        $(".showbill").remove();
        items = 0;
        if ($(".infinite-scroll-preloader").length == 0) {
            $(".infinite-scroll").append('<div class="infinite-scroll-preloader">' +
                '<div class="preloader"> </div>' +
                '</div>')
        }
        pageNum = 1;
        addItems(pageSize, 1);
    }
});