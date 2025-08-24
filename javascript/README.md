# JavaScript Utilities

Collection of JavaScript utilities and code snippets for web development.

## 📁 Contents

### touchScroll.js
Mobile-friendly horizontal carousel implementation with touch gesture support.

**Features:**
- Touch-based horizontal scrolling
- Configurable sensitivity
- Automatic boundary handling
- jQuery-based implementation

**Usage:**
```javascript
// Initialize horizontal scroll on carousel
myFunctions.addHorizontalSlideTo('.carousel-container .my-track', '.carousel-container .my-track .my-carousel-element');
```

**Parameters:**
- `trackSelector`: CSS selector for the scrollable track container
- `trackChildElementsSelector`: CSS selector for individual carousel elements

**Dependencies:**
- jQuery
- Touch-enabled device or browser

**HTML Structure Example:**
```html
<div class="carousel-container">
  <div class="my-track">
    <div class="my-carousel-element">Item 1</div>
    <div class="my-carousel-element">Item 2</div>
    <div class="my-carousel-element">Item 3</div>
  </div>
</div>
```

**CSS Requirements:**
```css
.carousel-container {
  overflow: hidden;
  width: 100%;
}

.my-track {
  display: flex;
  transition: transform 0.3s ease;
}

.my-carousel-element {
  flex: 0 0 auto;
  width: 300px; /* Adjust as needed */
}
```

## 🚀 Quick Start

1. Include jQuery in your project
2. Copy the `touchScroll.js` file to your project
3. Include the script in your HTML:
   ```html
   <script src="path/to/touchScroll.js"></script>
   ```
4. Set up your HTML structure as shown above
5. Initialize the touch scroll functionality

## 📝 Notes

- Sensitivity is set to 5px by default
- Swipe left to go to next items, swipe right to go to previous items
- The carousel loops back to the beginning when reaching the end
- Touch events are handled with proper cleanup to prevent memory leaks

## 🔧 Customization

To modify sensitivity, change the `sensitivityInPx` variable in the script:
```javascript
const sensitivityInPx = 10; // Increase for less sensitive touch detection
```

## 🐛 Known Issues

- Requires jQuery (consider vanilla JS version for modern projects)
- Uses older event handling patterns
- Could benefit from modern ES6+ syntax

## 🔄 Future Improvements

- [ ] Convert to vanilla JavaScript
- [ ] Add TypeScript definitions
- [ ] Implement modern event handling
- [ ] Add configuration options
- [ ] Support for vertical scrolling
- [ ] Add momentum scrolling
