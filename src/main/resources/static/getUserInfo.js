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
                `<a href='/logout' class='btn btn-primary btn-sm' role='button'>Sign out</a>
                <ul class="navbar-nav flex-row">
                <li class="nav-item me-3 me-lg-1 text-white">
                <a class="nav-link d-sm-flex align-items-sm-center" href="#">
                <img src="${data.linkAvatar}" class="rounded-circle" height="22" alt="" loading="lazy"/>`
            navBarInfo += `<span class="p-1">${getDataTime(data.name)}</span>`
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
                <img src = "${data.linkAvatar}" height = "190" alt = "" loading = "lazy" / >
                </div>
                <div class="col-md-6">
                <div class="card-body">
                <h6 class="card-title">${data.name}  ${data.lastName}</h6>
                <div class="mt-3">
                <p>Date birthday: ${data.birthday}</p>
                <p>City: ${data.city}</p>
                <p>Workplace: ${data.workplace}</p>
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

const getData = function () {
    let data = new Date()
    const year = data.getFullYear()
    const month = data.getMonth()
    const day = data.getDay()
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
    return day +' '+ fMonth +' '+ year
}

const getDataTime = function (name) {
    let data = new Date()
    const hour = data.getHours()
    if (hour > 4 && hour < 12) {
        return "Доброе утро, "+ name + ", сегодня " + getData()
    }
    if (hour > 12 && hour < 18) {
        return "Добрый день, "+ name + ", сегодня " + getData()
    }
    if (hour > 18 && hour < 23) {
        return "Добрый вечер, "+ name + ", сегодня " + getData()
    }
    if (hour > 18 && hour < 23) {
        return "Доброй ночи, "+ name + ", сегодня " + getData()
    }
}