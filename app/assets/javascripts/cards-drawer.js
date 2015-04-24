
/* Drawer configuration object */
var drawerParams = {
    w : 168,
    h : 244,
    scale : 0.7,
    cardOffsetH : 30,
    cardOffsetV : 40
};

function drawCard(orientation, ctx, cards, col, row, stackPosition) {
    stackPosition = typeof stackPosition !== 'undefined' ? stackPosition : 0;
    
    var w = drawerParams.w;
    var h = drawerParams.h;
    var s = drawerParams.scale;
    var offsetX = 0;
    var offsetY = 0;
    
    if (orientation == 'HORIZONTAL')
        offsetX = stackPosition * drawerParams.cardOffsetH * s;
    else if (orientation == 'VERTICAL')
        offsetY = stackPosition * drawerParams.cardOffsetV * s;
    
    ctx.drawImage(cards, col*(w - 0.5), row*(h - 0.5), w, h, offsetX, offsetY, w*s, h*s);
}

function getSuiteRow(suite) {
    if (suite == 'INVISIBLE')
        return 4;
    return ['CLUBS', 'DIAMONDS', 'HEARTS', 'SPADES'].indexOf(suite);
}

function getRankColumn(rank) {
    if (rank == 'INVISIBLE')
        return 2;
    return ['ACE', 'TWO', 'THREE', 'FOUR', 'FIVE', 
            'SIX', 'SEVEN', 'EIGHT', 'NINE', 'TEN', 
            'JACK', 'QUEEN', 'KING'].indexOf(rank);
}

function drawHandCards(orientation, ctx, cards, hand, visible) {
    if (visible) {
        for (var i = 0; i < hand.length; i++)
            drawCard(orientation, ctx, cards, getRankColumn(hand[i].rank), getSuiteRow(hand[i].suite), i);
    } else {
        for (var j = 0; j < hand; j++)
            drawCard(orientation, ctx, cards, getRankColumn('INVISIBLE'), getSuiteRow('INVISIBLE'), j);
    }

    return hand.length;
}

function drawHand(orientation, canvasId, hand) {

    var canvas = document.getElementById(canvasId);
    var ctx = canvas.getContext('2d');
    var cards = new Image();
    
    cards.onload = function() {
        drawHandCards(orientation, ctx, cards, hand, typeof hand !== 'number');
    };

    cards.src = '/assets/images/wikipedia-cards.png';
}

