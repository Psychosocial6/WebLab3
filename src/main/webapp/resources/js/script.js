function restoreGraphStateAfterAjax() {
    const svg = document.getElementById('image');
    if (!svg) return;
    const currentR = parseFloat(svg.getAttribute('data-r'));
    if (isNaN(currentR)) return;
    updateGraphLabels(currentR);
    drawAllPoints(currentR);
}

function handleRChange(newR) {
    localStorage.setItem('rValue', newR);
    const svg = document.getElementById('image');
    if (svg) {
        svg.setAttribute('data-r', newR);
    }
    updateGraphLabels(newR);
    repositionPoints(newR);
    updatePointsColor(newR);
}

function drawAllPoints(currentR) {
    const svg = document.getElementById('image');
    const historyStorage = document.querySelector('.history-data-storage');
    if (!svg || !historyStorage) return;

    const pointsDataJson = historyStorage.getAttribute('data-history');

    if (!pointsDataJson) return;

    const pointsData = JSON.parse(pointsDataJson);
    const scale = 150;
    svg.querySelectorAll('.dynamic-point').forEach(point => point.remove());

    pointsData.forEach(point => {
        const cx = 200 + (point.x / currentR) * scale;
        const cy = 200 - (point.y / currentR) * scale;
        const isHit = checkHit(point.x, point.y, currentR);
        const color = isHit ? 'green' : 'red';

        const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
        circle.setAttribute('r', '2');
        circle.setAttribute('cx', cx);
        circle.setAttribute('cy', cy);
        circle.setAttribute('fill', color);
        circle.setAttribute('class', 'dynamic-point');
        circle.setAttribute('data-x', point.x);
        circle.setAttribute('data-y', point.y);
        svg.appendChild(circle);
    });
}

function repositionPoints(newR) {
    const scale = 150;
    document.querySelectorAll('.dynamic-point').forEach(point => {
        const x = parseFloat(point.dataset.x);
        const y = parseFloat(point.dataset.y);
        const cx = 200 + (x / newR) * scale;
        const cy = 200 - (y / newR) * scale;
        point.setAttribute('cx', cx);
        point.setAttribute('cy', cy);
    });
}

function updatePointsColor(newR) {
    document.querySelectorAll('.dynamic-point').forEach(point => {
        const x = parseFloat(point.dataset.x);
        const y = parseFloat(point.dataset.y);
        const isHit = checkHit(x, y, newR);
        point.setAttribute('fill', isHit ? 'green' : 'red');
    });
}

function checkHit(x, y, r) {
    if (x >= 0 && y >= 0) {
        return (x <= r / 2) && (y <= -2 * x + r);
    } else if (x <= 0 && y >= 0) {
        return (x*x + y*y) <= (r * r);
    } else if (x <= 0 && y <= 0) {
        return (x >= -1 * r) && (y >= -1 * r / 2);
    }
    return false;
}

function handleGraphClick(event) {
    const svg = event.currentTarget;
    const rValue = parseFloat(svg.dataset.r);

    if (isNaN(rValue) || rValue === 0) {
        alert('Не выбрано значение R');
        return;
    }

    const rect = svg.getBoundingClientRect();

    const svgX = event.clientX - rect.left;
    const svgY = event.clientY - rect.top;

    const centerX = rect.width / 2;
    const centerY = rect.height / 2;

    const scale = rect.width * (150 / 400);

    const mathX = ((svgX - centerX) / scale) * rValue;
    const mathY = ((-(svgY - centerY)) / scale) * rValue;
    document.getElementById('mainForm:svg_x').value = mathX.toFixed(3);
    document.getElementById('mainForm:svg_y').value = mathY.toFixed(3);

    sendSvgPoint();
}

function updateGraphLabels(rValue) {
    if (isNaN(rValue) || rValue <= 0) return;

    const r = rValue.toFixed(1);
    const r_div_2 = (rValue / 2).toFixed(1);

    document.getElementById('graph-label-r-x').textContent = r;
    document.getElementById('graph-label-r-div-2-x').textContent = r_div_2;
    document.getElementById('graph-label-minus-r-x').textContent = -r;
    document.getElementById('graph-label-minus-r-div-2-x').textContent = -r_div_2;

    document.getElementById('graph-label-r-y').textContent = r;
    document.getElementById('graph-label-r-div-2-y').textContent = r_div_2;
    document.getElementById('graph-label-minus-r-y').textContent = -r;
    document.getElementById('graph-label-minus-r-div-2-y').textContent = -r_div_2;
}

function loadRFromStorage() {
    const savedR = localStorage.getItem('rValue');
    if (savedR) {
        const rValue = parseFloat(savedR);
        const buttonId = `mainForm:r-button-${rValue}`;
        const button = document.getElementById(buttonId);

        if (button) {
            button.click();
        }
    } else {
        const svg = document.getElementById('image');
        if (svg) {
            const initialR = parseFloat(svg.getAttribute('data-r'));
            updateGraphLabels(initialR);
        }
    }
}

window.addEventListener('DOMContentLoaded', loadRFromStorage);