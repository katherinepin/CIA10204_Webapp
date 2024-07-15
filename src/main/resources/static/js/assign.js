

document.addEventListener('DOMContentLoaded', function() {
    const calendar = document.getElementById('calendar');
    const form = document.getElementById('assignForm');
    const yearSelect = document.getElementById('yearSelect');
    const monthSelect = document.getElementById('monthSelect');
    const employees = [/* 這裡應該是從後端獲取的員工列表 */];
    console.log(employees);

    // 初始化年份選擇
    const currentYear = new Date().getFullYear();
    for (let year = currentYear - 1; year <= currentYear + 1; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearSelect.appendChild(option);
    }
    yearSelect.value = currentYear;

    // 初始化月份選擇
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.textContent = month + '月';
        monthSelect.appendChild(option);
    }
    monthSelect.value = new Date().getMonth() + 1;

    // 創建日曆
    function createCalendar(year, month) {
        calendar.innerHTML = ''; // 清空現有日曆
        const daysInMonth = new Date(year, month, 0).getDate();
        const firstDay = new Date(year, month - 1, 1).getDay();
        const dayNames = ['日', '一', '二', '三', '四', '五', '六'];
        
        // 添加星期幾的標題
        dayNames.forEach(day => {
            const dayHeader = document.createElement('div');
            dayHeader.className = 'day day-header';
            dayHeader.textContent = day;
            calendar.appendChild(dayHeader);
        });

        // 添加空白日期
        for (let i = 0; i < firstDay; i++) {
            const emptyDay = document.createElement('div');
            emptyDay.className = 'day empty';
            calendar.appendChild(emptyDay);
        }

        // 添加每一天的單元格
        for (let i = 1; i <= daysInMonth; i++) {
            const dayCell = document.createElement('div');
            dayCell.className = 'day';
            dayCell.innerHTML = `
                <div>${i}</div>
                <select name="employees">
                    <option value="">選擇員工</option>
                    ${employees.map(emp => `<option value="${empVO.Id}">${empVO.Name}</option>`).join('')}
                </select>
                <input type="hidden" name="dates" value="${year}-${month.toString().padStart(2, '0')}-${i.toString().padStart(2, '0')}">
            `;
            calendar.appendChild(dayCell);
        }
    }

    // 初始創建日曆
    createCalendar(currentYear, new Date().getMonth() + 1);

    // 當年份或月份改變時重新創建日曆
    yearSelect.addEventListener('change', updateCalendar);
    monthSelect.addEventListener('change', updateCalendar);

    function updateCalendar() {
        const selectedYear = parseInt(yearSelect.value);
        const selectedMonth = parseInt(monthSelect.value);
        createCalendar(selectedYear, selectedMonth);
    }

    // 表單提交
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        const formData = new FormData(form);
        fetch(form.action, {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            alert('排班保存成功');
            // 可以在這裡添加更多的成功後的操作
        })
        .catch(error => {
            console.error('Error:', error);
            alert('保存失敗，請重試');
        });
    });
});