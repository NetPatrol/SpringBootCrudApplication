const articleModal = document.getElementById('articleModal')
const am = new mdb.Modal(articleModal)
/**
 * Account Data
 **/
const articles = document.getElementById('articles')
const userInfo = document.getElementById('user-info')
const navBar = document.getElementById('navbar')
const login = document.getElementById('data-account')
const account = login.value
const getUserByLogin = async function(login) {
    let navBarInfo = ""
    let uInfo = ""
    let article = ""
    const url = '/user/' + login
    await fetch(url)
        .then(res => res.json())
        .then(data => {
            navBarInfo +=
                `<a href='/logout' class='btn btn-primary btn-sm' role='button'>Выйти</a>
                <ul class="navbar-nav flex-row">
                <li class="nav-item me-3 me-lg-1 text-white">
                <a class="nav-link d-sm-flex align-items-sm-center" href="#">
                <img src="${data.linkAvatar}" class="rounded-pill" height="22" alt="" loading="lazy"/>`
            navBarInfo += `<span class="p-1">${getTimesOfDayAndDate(data.name)}</span>`
            data.roles.forEach(r => {
                if (r.role === 'ROLE_ADMIN') {
                    navBarInfo +=
                        ` <span class="p-1">статус: администратор</span>
                        </a>
                        </li>
                        </ul>`
                } else if (r.role === 'ROLE_USER') {
                    navBarInfo +=
                        ` <span class="p-1">статус: пользователь</span>
                        </a>
                        </li>
                        </ul>`
                }
            })
            navBar.innerHTML = navBarInfo

            uInfo +=
               `<div class="col-md-4" >
                <img src = "${data.linkAvatar}"  class="img-fluid rounded ripple" height="190" alt = "" loading = "lazy"/>
                </div>
                <div class="col-md-6">
                <div class="card-body">
                <h6 class="card-title">${data.name}  ${data.lastName}</h6>
                <div class="mt-3">
                <p>Дата рождения: ${data.birthday}</p>
                <p>Город: ${data.city}</p>
                <p>Место работы: ${data.workplace}</p>
                </div>
                </div>
                </div>`
                userInfo.innerHTML = uInfo

            data.articles.forEach(a => {
                article +=
                    `<div class='card mt-3'>
                    <div class='card-body'>
                    <h5 class='card-title'>${a.title}</h5>
                    <p class='card-text'>${a.article}</p>
                    <p class='card-text'>
                    <small class='text-muted'>${a.date}</small>
                    </p>
                    </div>
                    </div>`
                articles.innerHTML = article
            })
        })
}

getUserByLogin(account).then()

const getDate = function () {
    let data = new Date()
    let year = data.getUTCFullYear()
    let month = data.getUTCMonth()
    let day = data.getUTCDate()
    let fMonth
    switch (month)
    {
        case 0: fMonth="января"; break;
        case 1: fMonth="февраля"; break;
        case 2: fMonth="марта"; break;
        case 3: fMonth="апреля"; break;
        case 4: fMonth="мае"; break;
        case 5: fMonth="июня"; break;
        case 6: fMonth="июля"; break;
        case 7: fMonth="августа"; break;
        case 8: fMonth="сентября"; break;
        case 9: fMonth="октября"; break;
        case 10: fMonth="ноября"; break;
        case 11: fMonth="декабря"; break;
    }
    return ' ' + day +' '+ fMonth +' '+ year
}

const getTimesOfDayAndDate = function (name) {
    let data = new Date()
    let hour = data.getHours()
    if (hour >= 4 && hour < 12) {
        return "Доброе утро, "+ name + ", сегодня " + getDate()
    }
    if (hour >= 12 && hour < 18) {
        return "Добрый день, "+ name + ", сегодня " + getDate()
    }
    if (hour >= 18 && hour < 23) {
        return "Добрый вечер, "+ name + ", сегодня " + getDate()
    }
    if (hour >= 0 && hour < 4) {
        return "Доброй ночи, "+ name + ", сегодня " + getDate()
    }
}




document.getElementById('add-article').onclick = function () {
    am.show()
}
const articleButton = articleModal.querySelector('.modal button#addBtn')
const bodyArticleModalInput = articleModal.querySelector('.modal-body input')
const bodyArticleModalTextArea = articleModal.querySelector('.modal-body textarea')
articleButton.onclick = function (ev) {
    ev.preventDefault()
    let articleData = new Date().toLocaleDateString()
    const url = '/articles'
    let uid = account
    let data =
        {
            "title": bodyArticleModalInput.value,
            "article": bodyArticleModalTextArea.value,
            "date": articleData,
            "user": uid
        }
    addArticle(url, data).then((res) => {
        if (res !== null) {
            getUserByLogin(uid).then()
            am.hide()
        }
    })
}

const addArticle = async function (url, data) {
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
        credentials: "same-origin"
    })
    if (response.ok){
        return await response.json();
    }
}