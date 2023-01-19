const quotes = ["“Learn as if you will live forever, live like you will die tomorrow.” — Mahatma Gandhi",
"Success is not final; failure is not fatal: It is the courage to continue that counts. — Winston S. Churchill", 
"The road to success and the road to failure are almost exactly the same. — Colin R. Davis", 
];

function getMotto() {
  alert(quotes[Math.floor(Math.random() * quotes.length)]);
  var countDownDate = new Date().getTime() +6000 ;//86400000

  var x = setInterval(function () {
    var now = new Date().getTime();

    var distance = countDownDate - now;

    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor(
      (distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    );
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    document.getElementById("countdown").innerHTML =
      days + "d " + hours + "h " + minutes + "m " + seconds + "s ";

    if (distance < 0) {
      clearInterval(x);
      document.getElementById("countdown").innerHTML = "Countdown Finished";
    }
  }, 1000);
}

$('button.cooldown').click(function(){
  var btn = $(this);
  btn.prop('disabled', true);
  setTimeout(function(){
    btn.prop('disabled', false);
  },15000);
});