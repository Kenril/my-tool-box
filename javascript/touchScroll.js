document.ready(function() {
  myFunctions.addHorizontalSlideTo('.carousel-container .my-track', '.carousel-container .my-track .my-carousel-element')
})


const myFunctions = myFunctions || {

  addHorizontalSlideTo: function(trackSelector, trackChildElementsSelector) {
    let track = document.querySelector(trackSelector);
    let carouselWidth = document.querySelector(trackChildElementsSelector).offsetWidth;

    let nbElements = document.querySelectorAll(trackChildElementsSelector).length;
    let maxSlide = carouselWidth * nbElements;

    let slided = 0;
    const sensitivityInPx = 5;
    $('.carousel-container').on('touchstart', function (event) {
      let xClick = event.originalEvent.touches[0].pageX;
      $(this).one('touchmove', function (event) {
        let xMove = event.originalEvent.touches[0].pageX;


        if (Math.floor(xClick - xMove) > sensitivityInPx) {
          slided -= carouselWidth;
          if (slided < -maxSlide) {
            slided = 0
          }
          track.style.transform = `translateX(${slided}px)`;
        } else if (Math.floor(xClick - xMove) < -sensitivityInPx) {

          slided += carouselWidth;
          if (slided > 0) {
            slided = 0;
          }
          track.style.transform = `translateX(${slided}px)`;
        }
      });
      $(this).on('touchend', function () {
        $(this).off('touchmove');
      });
    })
  }

}