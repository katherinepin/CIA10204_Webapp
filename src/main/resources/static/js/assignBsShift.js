const arrayColumn = (arr, n) => arr.map(x => x[n]);
const _dfSign = -1;  // Holiday 0: 自排, -1: 預設
var bsShift = {
    MyShift: [], clsChk: [], cntChk: [], hldCnt: [], arrHLD: [], arrWKD: [], shfitArray: [],
    dCnt: 0, mCnt: 0, hCnt: 0, shiftMonth: null, setX: null, setY: null, setValue: null,
    choiceShfit: -1,
    // initial
    initial: function(addMonth) {
        let dd = (addMonth == null) ? Date.now() : new Date(bsShift.shiftMonth.getFullYear(), bsShift.shiftMonth.getMonth() + 1 + addMonth, 0);
        this.iniMyShift(dd);
        this.showUI(0);
    },
    iniMyShift: function(dd) {
        this.shiftMonth = new Date((new Date(dd)).getFullYear(), (new Date(dd)).getMonth() + 1, 0);
        this.MyShift = new Array();
        this.dCnt = this.shiftMonth.getDate();
        this.mCnt = document.querySelectorAll('.clsRow .clsCol').length - 1; // 減去表頭行
        for (let i = 0; i < this.mCnt; i++) {
            this.MyShift[i] = new Array(this.dCnt).fill(_dfSign);
        }
        this.clsChk = new Array(this.dCnt).fill(_dfSign);
        this.arrHLD = new Array(this.dCnt).fill("");
        this.arrWKD = new Array(this.dCnt).fill("");
        this.cntChk = new Array(this.mCnt).fill(0);
        this.hldCnt = new Array(this.mCnt).fill(0);
        let myDate, YY, MM, wkDay;
        YY = this.shiftMonth.getFullYear();
        MM = this.shiftMonth.getMonth() + 1;
        for (let i = 0; i < this.clsChk.length; i++) {
            myDate = new Date(YY, MM - 1, (i + 1));
            wkDay = myDate.getDay();
            this.arrWKD[i] = data.WeekDay[wkDay];
            this.arrHLD[i] = (data.OwHoliDay.some(e => this.isEqualDate(e.d, myDate))) ? ((wkDay == "0" || wkDay == "6") ? "" : "其他") : (((wkDay == "0" || wkDay == "6") ? "Holiday" : ""));
        }
        // shfitArray
        this.shfitArray = new Array(this.mCnt).fill(0);
        let sum = 0;
        this.hCnt = this.mCnt; // 假日數量
        for (let i = 0; i < data.ShiftCnts.length; i++) {
            this.hCnt = this.hCnt - data.ShiftCnts[i];
            for (let j = 0; j < data.ShiftCnts[i]; j++) {
                this.shfitArray[sum] = i + 1;
                sum++;
            }
        }
    },
    genShift: function() {
        let retry = 0;
        let genResult = false;
        while (retry < 30) {
            this.iniMyShift(this.shiftMonth);
            if (this.genMyShift()) {
                genResult = true;
                break;
            } else {
                retry++;
                //console.log("retry:"+retry);
            }
        }
        bsShift.showUI();
        document.getElementById("result").innerHTML = (genResult) ? "" : "產製失敗需要重新產生";
    },
    // 20200624↓
    checkShift: function(Mem, Day, Shift) {
        let shiftArr = arrayColumn(this.MyShift, Day - 1);
        let contiArr = this.MyShift[Mem - 1].slice(0, Day - 1);
        // 目前該班別已排數量
        let shiftCntNow = shiftArr.map((e, idx) => { return (e == Shift) ? idx : "x" }).filter(e => e != "x").length;
        // 該班別應排數量
        let shiftCntNeed = (Shift == 0) ? this.hCnt : data.ShiftCnts[Shift - 1];
        if (shiftCntNow >= shiftCntNeed) { 
            return false;
        }
        if (Shift != 0) {
            let cc = contiArr.map((e) => { return (e > 0) ? "1" : "0" }).join("").split("0");
            let contiStr = cc[cc.length - 1];
            if (contiStr.length >= data.MaxShift) {
                return false;
            }
        }
        return true;
    },
    setShiftValue: function(Mem, Day, Value) {
        setX = Mem - 1;
        setY = Day - 1;
        setValue = Value;
        this.MyShift[Mem - 1][Day - 1] = Value;
    },
    genMyShift: function() {
        let iContCls;
        let array = bsRandom.shuffle(this.shfitArray);
        for (let i = 0; i < array.length; i++) {
            this.MyShift[i][0] = array[i];
            if (array[i] > 0) {
                iContCls = bsRandom.getMaxShiftRandom();
                for (let j = 1; j < iContCls; j++) {
                    this.MyShift[i][j] = array[i];
                }
            }
        }
        let ir = 0, irBK = 0;
        let Day = 2;
        while (Day <= this.dCnt) {
            array = this.shfitArray.slice();
            let iCnts = array.length;
            while (iCnts > 0) {
                let idx;
                for (let j = 0; j < this.mCnt; j++) {
                    idx = array.indexOf(this.MyShift[j][Day - 1]);
                    if (idx >= 0) {
                        array.splice(idx, 1);
                    }
                }
                bsRandom.shuffle(array);
                let unShitf = arrayColumn(this.MyShift, Day - 1).map((e, idx) => { return (e == _dfSign) ? idx : "Nan" }).filter(e => e != "Nan");
                let letsCHK = true;
                for (let k = 0; k < array.length; k++) {
                    if (this.checkShift(unShitf[k] + 1, Day, array[k])) {
                        this.setShiftValue(unShitf[k] + 1, Day, array[k]);
                        if (array[k] > 0) {
                            iContCls = bsRandom.getMaxShiftRandom();
                            let contiCnt = (Day + iContCls > this.dCnt) ? this.dCnt - Day : iContCls;
                            if (contiCnt > 1) {
                                for (let j = 1; j < contiCnt; j++) {
                                    if (this.checkShift(unShitf[k] + 1, Day + j, array[k])) {
                                        this.setShiftValue(unShitf[k] + 1, Day + j, array[k]);
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        letsCHK = false;
                        ir++;
                        break;
                    }
                }
                iCnts = (letsCHK) ? iCnts - 1 : 0;
                if (letsCHK == false) {
                    for (let j = 0; j < this.mCnt; j++) {
                        let maxPlus = (Day + 6 > this.dCnt) ? this.dCnt : Day + 6;
                        for (let k = Day; k < maxPlus; k++) {
                            this.MyShift[j][k - 1] = _dfSign;
                        }
                    }
                    Day -= 1;
                    if (ir > 20) {
                        for (let j = 0; j < this.mCnt; j++) {
                            let maxPlus = (Day + 6 > this.dCnt) ? this.dCnt : Day + 6;
                            for (let k = Day; k < maxPlus; k++) {
                                this.MyShift[j][k - 1] = _dfSign;
                            }
                        }
                        Day -= 1;
                        irBK += 1;
                        ir = 0;
                        if (irBK > 100) {
                            return false;
                        }
                    }
                }
            }
            Day++;
        }
        return true;
    },
    // 其他代碼省略
    showUIFrame: function() {
        let cssHD = "";
        let Head1 = "<div class='clsRow'>" + "<div class='clsHead'>" + this.shiftMonth.getFullYear() + "年" + "</div>";
        let Tail = "<div class='clsRow'>" + "<div class='clsTail'></div>";
        for (let i = 0; i < this.clsChk.length; i++) {
            cssHD = (this.arrHLD[i] == "") ? "" : " clsHoliday";
            let day = i + 1;
            let month = this.shiftMonth.getMonth() + 1;
            let weekDay = this.arrWKD[i];
            Head1 += `<div class='clsHead${cssHD}' id='H_${day}'>${month}/${day}(${weekDay})</div>`;
            Tail += `<div class='clsTail' id='T_${day}'></div>`;
        }
        Head1 += "</div>";
        Tail += "</div>";
        let iRow = 0, Body = "";
        this.MyShift.forEach(arrRow => {
            iRow++;
            Body += "<div class='clsRow'>";
            for (let i = 0; i <= arrRow.length; i++) {
                Body += "<div class='clsCol' id='M_" + iRow + "_" + i + "'></div>";
            }
            Body += "</div>";
        });
        document.getElementById("container").innerHTML = Head1 + Body + Tail;
        document.getElementById("divSfhit").innerHTML = data.ShiftCode.map((e, idx) => { return "<input  type='radio' name='choiceShfit' value='" + idx + "'>" + data.ShiftName[idx] }).join("");
        // 綁定事件
        document.getElementsByName("choiceShfit").forEach(e => {
            e.addEventListener('click', function () {
                bsShift.choiceShfit = this.value;
                document.getElementById("mouseShift").innerHTML = data.ShiftName[this.value];
                document.getElementById('divMouseTxt').style.display = "inline";
            });
        });
    },
    showUI: function(type) {
        // 清除手動排班選項
        this.choiceShfit = _dfSign;
        document.getElementById('divMouseTxt').style.display = "none";
        document.getElementById("mouseShift").innerHTML = "";
        document.getElementsByName("choiceShfit").forEach(e => { e.checked = false; });
        // 表頭
        if (type == 0) { this.showUIFrame(); }
        // 特殊日期
        data.OwHoliDay.forEach(e => {
            let holiday = new Date(e.d);
            if (holiday.getFullYear() == this.shiftMonth.getFullYear() && holiday.getMonth() == this.shiftMonth.getMonth()) {
                let cellHLD = document.getElementById("H_" + holiday.getDate());
                cellHLD.setAttribute("data-tooltip", e.m);
                cellHLD.classList.add("tooltip");
            }
        });
        // 顯示班表內容
        let vRow = 0;
        this.MyShift.forEach(arrRow => {
            vRow++;
            Temp = arrRow.map(e => { return (e >= 0) ? data.ShiftCode[e] : (type == 0) ? "　" : data.ShiftCode[0]; });
            let cell;
            for (let i = 0; i < Temp.length; i++) {
                cell = document.getElementById("M_" + vRow + "_" + (i + 1));
                cell.innerHTML = Temp[i];
                cell.classList.remove("IsHLD", "tooltip", "warning");
                if (Temp[i] == data.ShiftCode[0]) {
                    cell.classList.add("IsHLD");
                }
            }
        });
        // 綁定事件
        document.querySelectorAll(".clsCol:not(:first-child)").forEach(e => {
            e.addEventListener('click', function() {
                bsShift.modCell(this.id, bsShift.choiceShfit);
                return false;
            });
            e.oncontextmenu = function() {
                bsShift.modCell(this.id, _dfSign);
                return false;
            };
        });
    },
    isEqualDate: function(element, value) {
        let d = new Date(element);
        return (d.getDate() == value.getDate() && d.getFullYear() == value.getFullYear() && d.getMonth() == value.getMonth());
    },
    // 其他代碼省略
}
