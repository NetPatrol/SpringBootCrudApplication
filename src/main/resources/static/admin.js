const editButtons = document.querySelector('div#editButtons')
const editModal = document.getElementById('editModal')
const confirmModal = document.getElementById('confirmModal')
const em = new mdb.Modal(editModal)
const cm = new mdb.Modal(confirmModal)
/**
* Table All User
**/
const users = document.getElementById('users')
const renderUserTable = (array) => {
    let temp = ""
    array.forEach(u => {
        temp+=`<tr class="data-row">
               <td hidden>${u.id}</td>
               <td>${u.name}</td>
               <td>${u.lastName}</td>
               <td>${u.birthday}</td>
               <td>${u.city}</td>
               <td>${u.workplace}</td>
               <td>${u.login}</td>`
        u.roles.forEach(r => {
            if (r.role === "ROLE_ADMIN") {
                temp += `<td>admin</td>`
            } else if (r.role === "ROLE_USER") {
                temp += `<td>user</td>`
            }
        })
        temp+=`<td>${u.locked}</td>
               <td>
               <button
               id='eBtn'
               type='edt'
               class='btn btn-primary btn-sm'
               value='Edit'>
               edit</button></td>
               <td><button
               id="dBtn" 
               type='button'
               class='btn btn-primary btn-sm'
               value='Delete'>
               delete</button>
               </td>`
        })
        users.innerHTML = temp
}
const getUsers = async function(url) {
    await fetch(url)
        .then(res => res.json())
        .then(array => renderUserTable(array))
}
getUsers('/users').then()

/**
 * Account Data
 **/
const user = document.getElementById('user')
const login = document.getElementById('data-account')
const account = login.value
const getUserByLogin = async function(login) {
    let out = `<tr>`
    const url = '/user/' + login
    await fetch(url)
        .then(res => res.json())
        .then(data => {
            out += `<td hidden>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.lastName}</td>
                    <td>${data.login}</td>`;
            user.innerHTML = out;
        })
}

getUserByLogin(account).then()

/**
* Registration form
**/
const formRegistration = document.getElementById('registration')
const nameValueFormRegistrationInput = document.getElementById('create-name')
const lastValueFormRegistrationInput = document.getElementById('create-last')
const loginValueFormRegistrationInput = document.getElementById('create-login')
const birthdayValueFormRegistrationInput = document.getElementById('create-birthday')
const cityValueFormRegistrationInput = document.getElementById('create-city')
const workplaceValueFormRegistrationInput = document.getElementById('create-workplace')
const passwordValueFormRegistrationInput = document.getElementById('create-password')
const confirmValueFormRegistrationInput = document.getElementById('create-confirm')

formRegistration.addEventListener('submit', e => {
    e.preventDefault()
    const url = '/users'
    let data =
        {
            "name": nameValueFormRegistrationInput.value,
            "lastName": lastValueFormRegistrationInput.value,
            "birthday": birthdayValueFormRegistrationInput.value,
            "city": cityValueFormRegistrationInput.value,
            "workplace": workplaceValueFormRegistrationInput.value,
            "login": loginValueFormRegistrationInput.value,
            "password": passwordValueFormRegistrationInput.value,
            "confirmPassword": confirmValueFormRegistrationInput.value
        }
    addUser(url, data).then((res) => {

        if (res !== null) {
            getUsers(url).then();
            cm.show()
        } else {
            alert('ERROR')
        }

    })
    formRegistration.reset();
})

const addUser = async function (url, data) {
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

/**
 * Modal edit/delete form
 **/
const bodyEditModalInput = editModal.querySelectorAll('.modal-body input')
const bodyEditModalSelect = editModal.querySelector('.modal-body select')
document.querySelector('tbody#users').onclick = function (event) {
    if (event.target.tagName !== 'BUTTON') return  false
    console.log(event.target)
    let data = [...event.target.parentNode.parentNode.children]
    let text = getDataEditFromButton(data)
    bodyEditModalInput[0].value = text[0]
    bodyEditModalInput[1].value = text[1]
    bodyEditModalInput[2].value = text[2]
    bodyEditModalInput[3].value = text[3]
    bodyEditModalInput[4].value = text[4]
    bodyEditModalInput[5].value = text[5]
    bodyEditModalInput[6].value = text[6]
    if (event.target.value === 'Delete') {
        document.getElementById('ifNeedHeaderForDeleteModal').style.display = 'flex'
        document.getElementById('ifNeedHeaderEditModal').style.display = 'none'
        document.getElementById('ifEditName').style.display = 'none'
        document.getElementById('ifEditLast').style.display = 'none'
        document.getElementById('ifEditBirthday').style.display = 'none'
        document.getElementById('ifEditCity').style.display = 'none'
        document.getElementById('ifEditWorkplace').style.display = 'none'
        document.getElementById('ifEditLogin').style.display = 'none'
        document.getElementById('ifEditPassword').style.display = 'none'
        document.getElementById('ifEditConfirmPassword').style.display = 'none'
        document.getElementById('ifEditRole').style.display = 'none'
        document.getElementById('ifDeleteModal').style.display = 'block'
        document.getElementById('ifNeedDeleteButtonInModal').style.display = 'inline'
        document.getElementById('ifNeedEditButtonInModal').style.display = 'none'
        document.getElementById('ifNeedClearButtonInModal').style.display = 'none'
    }
    if (event.target.value === 'Edit') {
        document.getElementById('ifNeedHeaderForDeleteModal').style.display = 'none'
        document.getElementById('ifNeedHeaderEditModal').style.display = 'flex'
        document.getElementById('ifDeleteModal').style.display = 'none'
        document.getElementById('ifEditName').style.display = 'flex'
        document.getElementById('ifEditLast').style.display = 'flex'
        document.getElementById('ifEditBirthday').style.display = 'flex'
        document.getElementById('ifEditCity').style.display = 'flex'
        document.getElementById('ifEditWorkplace').style.display = 'flex'
        document.getElementById('ifEditLogin').style.display = 'flex'
        document.getElementById('ifEditPassword').style.display = 'flex'
        document.getElementById('ifEditConfirmPassword').style.display = 'flex'
        document.getElementById('ifEditRole').style.display = 'flex'
        document.getElementById('ifNeedDeleteButtonInModal').style.display = 'none'
        document.getElementById('ifNeedEditButtonInModal').style.display = 'inline'
        document.getElementById('ifNeedClearButtonInModal').style.display = 'inline'
    }
    em.show()
}

function getDataEditFromButton(data) {
    return data.map(item => item.textContent)
}

editButtons.onclick = function (ev) {
    ev.preventDefault()
    if (ev.target.tagName !== 'BUTTON') return false
    console.log(ev.target.value)
    const url = '/users'
    let roleIndex = bodyEditModalSelect.options.selectedIndex
    let data =
            {
                "id": bodyEditModalInput[0].value,
                "name": bodyEditModalInput[1].value,
                "lastName": bodyEditModalInput[2].value,
                "birthday": bodyEditModalInput[3].value,
                "city": bodyEditModalInput[4].value,
                "workplace": bodyEditModalInput[5].value,
                "login": bodyEditModalInput[6].value,
                "password": bodyEditModalInput[7].value,
                "confirmPassword": bodyEditModalInput[8].value,
                "roles": [{
                    "id": roleIndex,
                    "role": bodyEditModalSelect.options[roleIndex].value
                }]
            }
    if (ev.target.value === 'Update') {
        editUser(url, data).then((res) => {
            if (res !== null) {
                getUsers(url).then();
                em.hide()
                cm.show()
            }
        })
    } else if (ev.target.value === 'Delete') {
        deleteUser(url, data).then((res) => {
            if (res !== null) {
                getUsers(url).then();
                em.hide()
                cm.show()
           }
        })
    }
}

const deleteUser = async function (url, data) {
    let response = await fetch(url, {
        method: 'DELETE',
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

const editUser = async function (url, data) {
    let response = await fetch(url, {
        method: 'PUT',
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