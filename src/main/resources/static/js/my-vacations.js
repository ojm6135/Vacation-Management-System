function changeBackgroundOfRow(button, color) {
    const row = button.closest('tr');  // 클릭된 버튼의 가장 가까운 tr 요소 찾기
    row.style.backgroundColor = color;
}

function changeBtnDesign(button, text, color) {
    button.textContent = text;
    button.style.backgroundColor = color;
}

function fillModifyForm(userId, row) {
    const cells = row.querySelectorAll('td');
    const cellValues = Array.from(cells).map(cell => cell.textContent.trim());
    const modifyForm = document.getElementById("modify-form");

    const vacationId = cellValues[0];
    const startDate = cellValues[1];
    const endDate = cellValues[2];
    const reason = cellValues[4];

    modifyForm.action = '/users/' + userId + "/vacations/" + vacationId;

    document.getElementById("modified-start-date").value = startDate;
    document.getElementById("modified-end-date").value = endDate;
    document.getElementById("modified-reason").value = reason;

    const typeSelect = document.getElementById("modified-leave-type");

    // Select 박스 값 설정
    const typeValue = cellValues[3];
    for (let option of typeSelect.options) {
        if (option.textContent === typeValue) {
            option.selected = true;
            break;
        }
    }
}

function modifyBtnEvent(userId, button) {
    const modifyForm = document.getElementById("modify-form-container");
    const formStatus = window.getComputedStyle(modifyForm).display;
    const row = button.closest('tr');

    // 클릭된 버튼이 수정상태일 때
    if (button.textContent === "수정") {
        if (formStatus === "block") {  // 수정 폼이 열려 있을 때
            // 행의 배경색 바꿈, 취소 버튼을 수정 버튼으로 바꿈
            const btns = Array.from(document.getElementsByClassName("modify-btn"));
            const filteredBtns = btns.filter(btn => btn.textContent === "취소");

            filteredBtns.forEach(btn => {
                changeBackgroundOfRow(btn, "");
                changeBtnDesign(btn, "수정", "#4CAF50");
            })
        } else if (formStatus === "none") {  // 수정 폼이 닫혀 있을 때
            modifyForm.style.display = "block";
        }

        changeBtnDesign(button, "취소", "#FFA500");
        changeBackgroundOfRow(button, "#f5f5f5");
    }
    // 클릭된 버튼이 취소상태일 때
    else if (button.textContent === "취소") {
        modifyForm.style.display = "none";
        changeBtnDesign(button, "수정", "#4CAF50");
        changeBackgroundOfRow(button, "");
    }

    fillModifyForm(userId, row)
}

function deleteVacation(userId, vacationId) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        const form = document.createElement('form');

        form.method = 'post';
        form.action = '/users/' + userId + "/vacations/" + vacationId;

        const methodInput = document.createElement('input');
        methodInput.type = 'hidden';
        methodInput.name = '_method';
        methodInput.value = 'delete';
        form.appendChild(methodInput);

        document.body.appendChild(form);
        form.submit();
    }
}