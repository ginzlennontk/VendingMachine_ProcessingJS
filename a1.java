int coin = 0;
int[] button_posX = {0, 0, 0, 0, 0};
int[] productPrice = {59, 70, 45, 39, 45};
int[] productNum = {50, 2, 50, 50, 1};


void setup() {
  size(900, 700);
}


void draw() {
  background(#FFFFFF);
  vendingMachine(50, 50);
  fill(0);
  textSize(20);
  textAlign(LEFT, CENTER);
  text("Coin "+coin, 50, 20);
  //int a = 10, b = 25, c = 50, d = 25, e = 3;
  String[] productName = {"Math\n1", "ProFun\nProcessing\n&\nPython", "Physic\n1", "Man\n&\nSociety", "English"};
  int buttonX = 110;
  int product_posX = 85;
  int productNum_posX = 120;
  int num = 1;
  int product = 0;
  while (num<=productName.length) {
    showProductNum(productNum_posX, productNum[product]);
    showProduct(product_posX, productName[product]);    
    buttonBuy(coin, productPrice[product], buttonX, product);
    button_posX[product] = buttonX;
    num++;
    product++;
    product_posX += 150;
    buttonX += 150;
    productNum_posX += 150;
  }
  //mousePressed(button_posX, productPrice);
}


void vendingMachine(int x, int y) {
  int machineWidth = 800, machineHeight = 600;
  fill(#FF99FF);
  rect(x, y, machineWidth, machineHeight);
}


void buttonBuy(int coin, int productPrice, int x, int i) {
  int y = 300;
  int buttonWidth = 80, buttonHeight = 40;
  int buttonColor;
  int available = #00FF00;
  int unavailable = 255;
  if (coin>=productPrice && productNum[i] > 0 ) {
    buttonColor = available;
  } else {
    buttonColor = unavailable;
  }
  strokeWeight(3);
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, 50);
  fill(0);
  textAlign(CENTER, CENTER);
  text(productPrice, x+(buttonWidth/2), y+(buttonHeight/2));
}


void showProduct(int x, String productName) { 
  int y = 120;
  final int productWidth = 130, productHeight = 170;
  fill(255);
  rect(x, y, productWidth, productHeight);
  fill(0);
  textAlign(CENTER);
  text(productName, x+(productWidth/2), y+(productHeight/3.5));
}


void showProductNum(int x, int productNum) {
  int y = 70;
  int productNum_width = 60, productNum_height = 30;
  rect(x, y, productNum_width, productNum_height);
  fill(#00DD00);
  textAlign(CENTER, CENTER);
  text(productNum, x+(productNum_width/2), y+(productNum_height/2));
}


void mousePressed() {
  int i = 0;
  while (i < button_posX.length) {
    if (coin >= productPrice[i] && productNum[i] > 0 && mouseButton == LEFT && (mouseX >= button_posX[i] && mouseX <= button_posX[i]+80) && (mouseY >= 300 && mouseY <= 300+40)) {
      coin -= productPrice[i];
      productNum[i] -= 1;
    }
    i++;
  }
}


void keyPressed() {
  if (key == 'z' || key == 'Z') {
    coin += 50;
  } else if (key == 'x' || key == 'X') {
    coin -= 50;
    if (coin <= 0) {
      coin = 0;
    }
  }
}

