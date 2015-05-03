
var BoundingRect = function(startX, startY, width, height) {
    this.startX = startX;
    this.startY = startY;
    this.width = width;
    this.height = height;

    this.containsPoint = function(x, y) {
        return x >= this.startX && x <= this.startX + this.width &&
               y >= this.startY && y <= this.startY + this.height;
    };
};

var Card = function(rank, suite) {
    this.rank = rank;
    this.suite = suite;
}; 

Card.prototype.getGridRow = function() {
    if (this.suite == 'INVISIBLE')
        return 4;
    return ['CLUBS', 'DIAMONDS', 'HEARTS', 'SPADES'].indexOf(this.suite);
};

Card.prototype.getGridCol = function() {
    if (this.rank == 'INVISIBLE')
        return 2;
    return ['ACE', 'TWO', 'THREE', 'FOUR', 'FIVE', 
            'SIX', 'SEVEN', 'EIGHT', 'NINE', 'TEN', 
            'JACK', 'QUEEN', 'KING'].indexOf(this.rank);
};

/* Great naming */
Card.makeInvisibleHand = function(handSize) {
    var result = [];
    while (handSize--)
        result.push(new Card('INVISIBLE', 'INVISIBLE'));
    return result;
};

var HandDrawer = function(config, position) {
    this.canvas = config.canvas;
    this.context = this.canvas.getContext('2d');
    this.imageGrid = config.imageGrid;
    this.cardWidth = config.cardWidth;
    this.cardHeight = config.cardHeight;
    this.cardScale = config.cardScale;
    this.position = position;
    this.nextCardDisplacementH = config.nextCardDisplacementH;
    this.nextCardDisplacementV = config.nextCardDisplacementV;
    this.numOfDrawnCards = 0;
};

HandDrawer.prototype.drawHand = function(hand) {
    var startX = 0;
    var startY = 0;
    var handLengthInPixels = 0;
    var tableBorderInPixels = this.cardScale * this.cardWidth * 0.5;
   
    /* OK, let's prentend this to be the stack of cards:
        .--.--.---.
        |A |2 |3  | 
        |  |  |   |
        '--'--'---'
        ↑  ↑
        
       Distance between these arrows is "nextCardDisplacementH", which is 
       just a displacement between two stacked cards. */
    if (this.position == 'top' || this.position == 'bottom')
        handLengthInPixels = (hand.length - 1) * this.nextCardDisplacementH + this.cardWidth;
    else
        handLengthInPixels = (hand.length - 1) * this.nextCardDisplacementV + this.cardHeight;

    handLengthInPixels *= this.cardScale;
    startX = (this.canvas.width  - handLengthInPixels) * 0.5;
    startY = (this.canvas.height - handLengthInPixels) * 0.5;
    
    if (this.position == 'top')
        startY = tableBorderInPixels;
    else if (this.position == 'bottom')
        startY = this.canvas.height - this.cardHeight * this.cardScale - tableBorderInPixels;
    else if (this.position == 'left') 
        startX = tableBorderInPixels;
    else
        startX = this.canvas.width - this.cardWidth * this.cardScale - tableBorderInPixels;
    
    this.numOfDrawnCards = 0;
    var boundingRect;
    var handBoundingRects = [];
    for (var i = 0; i < hand.length; i++) {
        boundingRect = this.drawCard(hand[i], startX, startY);
        
        if (i < hand.length-1) {
            if (this.position == 'top' || this.position == 'bottom')
                boundingRect.width = this.nextCardDisplacementH * this.cardScale;
            else
                boundingRect.height = this.nextCardDisplacementV * this.cardScale;
        }
        handBoundingRects.push(boundingRect);
    }
    return handBoundingRects;
};

HandDrawer.prototype.drawCard = function(card, startX, startY) {
    var displacementX = 0;
    var displacementY = 0;
    
    /* Displacements between stacked cards are computed there */
    if (this.position == 'top' || this.position == 'bottom')
        displacementX = this.numOfDrawnCards * this.nextCardDisplacementH * this.cardScale;
    else 
        displacementY = this.numOfDrawnCards * this.nextCardDisplacementV * this.cardScale;
    
    this.context.drawImage(this.imageGrid,
                           card.getGridCol() * (this.cardWidth - 0.5),
                           card.getGridRow() * (this.cardHeight - 0.5),
                           this.cardWidth,
                           this.cardHeight,
                           startX + displacementX,
                           startY + displacementY,
                           this.cardWidth * this.cardScale,
                           this.cardHeight * this.cardScale);
    this.numOfDrawnCards++;

    /* Bounding rectangle of drawn card is returned. */
    return new BoundingRect(startX + displacementX, startY + displacementY, 
                            this.cardWidth * this.cardScale,
                            this.cardHeight * this.cardScale);
};

var TableDrawer = function(canvas, config) {
    this.config = config;
    this.canvas = canvas;
    this.context = canvas.getContext('2d');
    this.width = canvas.width;
    this.height = canvas.height;
    this.boundingRects = { north: null, south: null, 
                           east: null, west: null };
};

TableDrawer.prototype.clear = function() {
    this.context.clearRect(0, 0, this.width, this.height);
};

TableDrawer.prototype.fit = function() {
    this.canvas.width = this.width;
    this.canvas.height = this.height;
};
 
TableDrawer.prototype.draw = function(hands) {
   if (hands === null)
        return;

    this.fit();
    this.clear();

    /* TODO: should not be hardcoded */
    this.context.fillStyle = '#408800';
    this.context.fillRect(0, 0, this.width, this.height);
    var image = new Image();
    image.src = this.config.imageGrid;

    var config = { canvas : this.canvas,
                   cardWidth : this.config.cardWidth,
                   cardHeight : this.config.cardHeight,
                   cardScale : this.config.cardScale,
                   imageGrid : image,
                   nextCardDisplacementH : this.config.nextCardDisplacementH,
                   nextCardDisplacementV : this.config.nextCardDisplacementV };
    
    var drawer = this;
    image.onload = function() {
        drawer.boundingRects.north = (new HandDrawer(config, 'top')).drawHand(hands.north);
        drawer.boundingRects.south = (new HandDrawer(config, 'right')).drawHand(hands.east);
        drawer.boundingRects.east = (new HandDrawer(config, 'bottom')).drawHand(hands.south);
        drawer.boundingRects.west = (new HandDrawer(config, 'left')).drawHand(hands.west);
    };
};

TableDrawer.prototype.drawBoundingRect = function(rect) {
    this.context.strokeStyle = 'red';
    this.context.lineWidth = 4;
    this.context.strokeRect(rect.startX, rect.startY, rect.width, rect.height);
};

var Table = function(canvasId, drawerConfig) {
    this.canvas = document.getElementById(canvasId);
    this.drawer = new TableDrawer(this.canvas, drawerConfig);
    this.hands = null;

    var table = this;
    this.eventHandler = function(evt) {
        var x = evt.clientX - table.canvas.getBoundingClientRect().left;
        var y = evt.clientY - table.canvas.getBoundingClientRect().top;

        /* Iterate through cards bounding rectangles */ 
        for (var position in table.drawer.boundingRects) {
            for (var i in table.drawer.boundingRects[position]) {
                var rect = table.drawer.boundingRects[position][i];
    
                if (rect.containsPoint(x, y)) {
                    table.drawer.drawBoundingRect(rect);
                    break;
                }
            }
        }
    };
    this.canvas.addEventListener('mousedown', this.eventHandler);
};

Table.prototype.draw = function() {
    this.drawer.draw(this.hands);
};

Table.prototype.setHands = function(hands) {
    this.hands = hands;
    this.draw();
};

