function takeBook(bookId) {
    $.post("takeBook", {bookId: bookId}, function () {
        redirect("availableBooks");
    });
}

function createRequest() {
    $.post("createRequest", {bookId: getValueSelectedItem("selectBook")}, function () {
        redirect("giveBook");
    });
}

function recommendBook() {
    $.post("createRecommend", {
        bookId: getValueSelectedItem("selectBook"),
        userId: getValueSelectedItem("selectReader"),
        text: $("#textRecommendation").val()
    }, function () {
        redirect("recommendBook");
    });
}

function deleteNotice(noticeId) {
    $.post("deleteNotice", {noticeId: noticeId}, function () {
        redirect("notices");
    });
}

function rateBook() {
    var radio = $("input[type=radio][name=rdo]").filter(":checked")[0];

    if (radio) {
        $.post("createRateBook", {bookId: getValueSelectedItem("selectBook"), rate: radio.value}, function () {
            redirect("menu");
        });
    }
}

function createBook() {
    var title = document.getElementById("title").value;
    var author = document.getElementById("author").value;

    $.post("addBook", {title: title, author: author}, function () {
        redirect("menu");
    });
}

function returnBook(bookId) {
    $.post("returnBook", {bookId: bookId}, function () {
        redirect("myBooks");
    });
}

function deleteBook(bookId) {
    $.post("deleteBook", {bookId: bookId}, function () {
        redirect("books");
    });
}

function deleteUser(userId) {
    $.post("deleteUser", {id: userId}, function () {
        redirect("users");
    });
}

function changeBlock(userId) {
    $.post("changeBlock", {id: userId}, function () {
        redirect("users");
    });
}

function deleteRecommendation(recommendationId) {
    $.post("deleteRecommendation", {recommendationId: recommendationId}, function () {
        redirect("menu");
    });
}

function deleteRate(rateId) {
    $.post("deleteRate", {rateId: rateId}, function () {
        redirect("menu");
    });
}

function getValueSelectedItem(item) {
    var params = document.getElementById(item);
    return params.options[params.selectedIndex].value;
}

function redirect(href) {
    window.location.href = href;
}
