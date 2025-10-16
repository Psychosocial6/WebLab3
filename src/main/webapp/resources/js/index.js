document.addEventListener("DOMContentLoaded", () => {
    const hourHand = document.querySelector(".hour-hand");
    const minuteHand = document.querySelector(".minute-hand");
    const secondHand = document.querySelector(".second-hand");
    const updateInterval = 8000;

    function updateClock() {
        let now = new Date();

        let hours = now.getHours();
        let minutes = now.getMinutes();
        let seconds = now.getSeconds();

        let hoursDeg = (360 / 12) * (hours % 12 + minutes / 60);
        let minutesDeg = (360 / 60) * (minutes + seconds / 60);
        let secondsDeg = (360 / 60) * seconds;

        hourHand.style.tranform = `translateX(-50%) rotate(${hoursDeg}deg)`;
        minuteHand.style.transform = `translateX(-50%) rotate(${minutesDeg}deg)`;
        secondHand.style.transform = `translateX(-50%) rotate(${secondsDeg}deg)`;
    }

    setInterval(updateClock, updateInterval);
    updateClock();
});