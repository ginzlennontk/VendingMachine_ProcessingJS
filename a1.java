int coin = 0;
int product_in_outletBox = 0;
//int[] button_posX = {0, 0, 0, 0, 0};
int[] productNum = {4, 2, 5, 5, 1};
int[] productPrice = {59, 70, 45, 39, 45};
String[] productName = {"Math\n1", "ProFun\nProcessing\n&\nPython", "Physic\n1", "Man\n&\nSociety", "English"};
int change = 0;

int a =0;
void setup() {
  size(900, 600);
}


void draw() {
  background(#FFFFDD);
  vendingMachine(50, 50);
  fill(0);
  textSize(20);
  textAlign(LEFT, CENTER);
  text("Coin "+coin, 50, 20);
  //int a = 10, b = 25, c = 50, d = 25, e = 3;
}


void vendingMachine(int x, int y) {
  int machineWidth = 800, machineHeight = 530;
  fill(#FF99FF);
  rect(x, y, machineWidth, machineHeight);

  int buttonX = x+60;
  int product_posX = x+35;
  int productNum_posX = x+70;
  int product = 0;

  while (product < productName.length) {
    showProductNum(productNum_posX, productNum[product]);
    showProduct(product_posX, productName[product]);    
    buttonBuy(buttonX, product);
    //button_posX[product] = buttonX;
    product++;
    product_posX += 150;
    buttonX += 150;
    productNum_posX += 150;
  }
  outletBox();
  statusDisplay();
}


void showProduct(int x, String productName) { 
  int y = 120;
  final int productWidth = 130, productHeight = 170;
  fill(255);
  strokeWeight(3);
  rect(x, y, productWidth, productHeight);
  fill(0);
  textAlign(CENTER);
  text(productName, x+(productWidth/2), y+(productHeight/3.5));
}


void showProductNum(int x, int productNum) {
  int y = 70;
  int productNum_width = 60, productNum_height = 30;
  fill(0);
  rect(x, y, productNum_width, productNum_height);
  fill(#00DD00);
  textAlign(CENTER, CENTER);
  text(productNum, x+(productNum_width/2), y+(productNum_height/2));
}



void buttonBuy(int x, int i) {
  int y = 300;
  final int buttonWidth = 80, buttonHeight = 40;
  int buttonColor;
  int available = #00FF00;
  int unavailable = 255;

  if (coin>=productPrice[i] && productNum[i] > 0 && a == 0) {
    buttonColor = available;
  } else {
    buttonColor = unavailable;
  }

  strokeWeight(3);
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, 50);
  fill(0);
  textAlign(CENTER, CENTER);
  text(productPrice[i], x+(buttonWidth/2), y+(buttonHeight/2));

  if (product_in_outletBox == 0 && mousePressed && coin >= productPrice[i] && productNum[i] > 0 && (mouseX >= x && mouseX <= x+buttonWidth) && (mouseY >= y && mouseY <= y+buttonHeight)) {
    coin -= productPrice[i];
    productNum[i] -= 1;
    product_in_outletBox = 1;
    a = 1;
    change = coin;
    coin = 0;
  }
}
//void mousePressed() {
//  int i = 0;
//  while (i < button_posX.length) {
//    if (coin >= productPrice[i] && productNum[i] > 0 && (mouseX >= button_posX[i] && mouseX <= button_posX[i]+80) && (mouseY >= 300 && mouseY <= 300+40)) {
//      coin -= productPrice[i];
//      productNum[i] -= 1;
//    }
//    i++;
//  }
//}



void outletBox() {
  fill(0, 200);
  rect(85, 370, 280, 190);
  if (product_in_outletBox == 1 && mousePressed && (mouseX >= 85 && mouseX <= 85+430) && (mouseY >= 400 && mouseY <= 400+150)) {
    product_in_outletBox = 0;
    a = 0;
  }
  if (a ==1 ) {
    productInOutletBox();
  }
}



void productInOutletBox() {
  fill(255);
  int productX = 125, productY = 505;
  int productWidth = 200, productWeight = 40;
  int lineX = productX, lineY = productY;
  rect(productX, productY, productWidth, productWeight);
  while (lineY < productY+productWeight) {
    strokeWeight(1);
    line(lineX, lineY, lineX+productWidth, lineY);
    lineY += 5;
  }
}



void statusDisplay() {
  fill(100);
  strokeWeight(0);
  rect(615, 370, 220, 150);
  fill(255);
  if (coin == 0 && a == 0) {
    text("Please Insert Coin", 700, 450);
  } else if (coin > 0 && a == 0) {
    text("Coin "+coin, 700, 450);
  } else if (a == 1) {
    text("Change "+change, 700, 450);
  }
}



void keyPressed() {
  if (a == 0 && key == 'z' || key == 'Z') {
    coin += 50;
  } else if (key == 'x' || key == 'X') {
    coin -= 50;
    if (coin <= 0) {
      coin = 0;
    }
  }
}