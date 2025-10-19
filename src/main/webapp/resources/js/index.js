document.addEventListener("DOMContentLoaded", () => {
    const hourHand = document.querySelector(".hour-hand");
    const minuteHand = document.querySelector(".minute-hand");
    const secondHand = document.querySelector(".second-hand");
    const dateDisplay = document.querySelector(".date-display");
    const updateInterval = 8000;

    function updateTimeAndDate() {
        const now = new Date();
        const hours = now.getHours();
        const minutes = now.getMinutes();
        const seconds = now.getSeconds();

        const hoursDeg = (360 / 12) * (hours % 12 + minutes / 60);
        const minutesDeg = (360 / 60) * (minutes + seconds / 60);
        const secondsDeg = (360 / 60) * seconds;

        hourHand.style.transform = `translateX(-50%) rotate(${hoursDeg}deg)`;
        minuteHand.style.transform = `translateX(-50%) rotate(${minutesDeg}deg)`;
        secondHand.style.transform = `translateX(-50%) rotate(${secondsDeg}deg)`;

        if (dateDisplay) {
            const day = String(now.getDate()).padStart(2, '0');
            const month = String(now.getMonth() + 1).padStart(2, '0');
            const year = now.getFullYear();
            const formattedDate = `${day}.${month}.${year}`;
            dateDisplay.textContent = formattedDate;
        }
    }

    setInterval(updateTimeAndDate, updateInterval);
    updateTimeAndDate();
});