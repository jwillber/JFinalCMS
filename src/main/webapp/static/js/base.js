function NavToggle() {
    $(".navbar-minimalize").trigger("click")
}
function SmoothlyMenu() {
    $("body").hasClass("mini-navbar") ? $("body").hasClass("fixed-sidebar") ? ($("#side-menu").hide(), setTimeout(function() {
        $("#side-menu").fadeIn(500)
    },
    300)) : $("#side-menu").removeAttr("style") : ($("#side-menu").hide(), setTimeout(function() {
        $("#side-menu").fadeIn(500)
    },
    100))
}
function localStorageSupport() {
    return "localStorage" in window && null !== window.localStorage
}
$(document).ready(function() {
    function e() {
        var e = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", e + "px")
    }
    $("#side-menu").metisMenu(),
    $(".right-sidebar-toggle").click(function() {
        $("#right-sidebar").toggleClass("sidebar-open")
    }),
    $(".sidebar-container").slimScroll({
        height: "100%",
        railOpacity: .4,
        wheelStep: 10
    }),
    $(".open-small-chat").click(function() {
        $(this).children().toggleClass("fa-comments").toggleClass("fa-remove"),
        $(".small-chat-box").toggleClass("active")
    }),
    $(".small-chat-box .content").slimScroll({
        height: "234px",
        railOpacity: .4
    }),
    $(".check-link").click(function() {
        var e = $(this).find("i"),
        a = $(this).next("span");
        return e.toggleClass("fa-check-square").toggleClass("fa-square-o"),
        a.toggleClass("todo-completed"),
        !1
    }),
    $(function() {
        $(".sidebar-collapse").slimScroll({
            height: "100%",
            railOpacity: .9,
            alwaysVisible: !1
        })
    }),
    $(".navbar-minimalize").click(function() {
        $("body").toggleClass("mini-navbar"),
        SmoothlyMenu()
    }),
    e(),
    $(window).bind("load resize click scroll",
    function() {
        $("body").hasClass("body-small") || e()
    }),
    $(window).scroll(function() {
        $(window).scrollTop() > 0 && !$("body").hasClass("fixed-nav") ? $("#right-sidebar").addClass("sidebar-top") : $("#right-sidebar").removeClass("sidebar-top")
    }),
    $(".full-height-scroll").slimScroll({
        height: "100%"
    }),
    $("#side-menu>li").click(function() {
        $("body").hasClass("mini-navbar") && NavToggle()
    }),
    $("#side-menu>li li a").click(function() {
        $(window).width() < 769 && NavToggle()
    }),
    $(".nav-close").click(NavToggle),
    /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent) && $("#content-main").css("overflow-y", "auto")
}),
$(window).bind("load resize",
function() {
    $(this).width() < 769 && ($("body").addClass("mini-navbar"), $(".navbar-static-side").fadeIn())
})