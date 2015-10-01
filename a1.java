int coin = 0;
int product_in_outletBox = 0;
//int[] button_posX = {0, 0, 0, 0, 0};
int[] productNum = {4, 2, 5, 5, 1};
int[] productPrice = {59, 70, 45, 39, 45};
String[] productName = {"Math 1", "ProFun", "Physic 1", "Man & Society", "English"};
int[] soldProduct = {0,0,0,0,0};
int change = 0;
int[] changeCoin = {0, 0, 0};
int a =0;
int page = 1;

void setup() {
  size(850, 450);
}


void draw() {
  background(#DDFFFF);
  textSize(15);

  if (page == 1) {
    display_page();
  } else if (page == 2){
    admin_page();
  }

  //int a = 10, b = 25, c = 50, d = 25, e = 3;
}



void display_page() {
  vendingMachine(20, 15);
  changeToAdmin_button();
}


void vendingMachine(int x, int y) {
  int machineWidth = 670, machineHeight = 420;
  fill(#FF99FF);
  rect(x, y, machineWidth, machineHeight);

  int productNum_posX = x+55;
  int product_posX = x+20;
  int buttonX = x+45;
  int product = 0;

  while (product < productName.length) {
    showProductNum(productNum_posX, y+20, productNum[product]);
    showProduct(product_posX, y+50, productName[product]);    
    buttonBuy(buttonX, y+200, product);
    //button_posX[product] = buttonX;
    product++;
    product_posX += 130;
    buttonX += 130;
    productNum_posX += 130;
  }
  outletBox(x+20, y+250);
  statusDisplay(x+450, y+250);
}



void showProductNum(int x, int y, int productNum) {
  //int y = 70;
  int productNum_width = 40, productNum_height = 20;
  strokeWeight(3);
  fill(0);
  rect(x, y, productNum_width, productNum_height);
  fill(#00DD00);
  textAlign(CENTER, CENTER);
  text(productNum, x+(productNum_width/2), y+(productNum_height/2));
}



void showProduct(int x, int y, String productName) { 
  //int y = 120;
  final int productWidth = 110, productHeight = 140;
  fill(255);
  strokeWeight(3);
  rect(x, y, productWidth, productHeight);
  fill(0);
  textAlign(CENTER);
  text(productName, x+(productWidth/2), y+(productHeight/3.5));
}




void buttonBuy(int x, int y, int i) {
  //int y = 300;
  final int buttonWidth = 60, buttonHeight = 30;
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
    soldProduct[i] += 1;
    coin -= productPrice[i];
    productNum[i] -= 1;
    product_in_outletBox = 1;
    a = 1;
    change = coin;
    changeCoin[0] = change/10;
    changeCoin[1] = (change%10)/5;
    changeCoin[2] = (change%10)%5;
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



void outletBox(int x, int y) {
  fill(0, 200);
  rect(x, y, 240, 150);
  if (product_in_outletBox == 1 && mousePressed && (mouseX >= x && mouseX <= x+240) && (mouseY >= y && mouseY <= y+150)) {
    product_in_outletBox = 0;
    a = 0;
  }
  if (a ==1 ) {
    productInOutletBox(x+30, (y+150)-35);
  }
}



void productInOutletBox(int x, int y) {
  fill(255);
  int productWidth = 180, productWeight = 30;
  int lineX = x, lineY = y;
  rect(x, y, productWidth, productWeight);
  while (lineY < y+productWeight) {
    strokeWeight(1);
    line(lineX, lineY, lineX+productWidth, lineY);
    lineY += 3;
  }
}



void statusDisplay(int x, int y) {
  int display_width = 200, display_height = 120;
  fill(100);
  strokeWeight(0);
  rect(x, y, display_width, display_height);
  fill(255);
  textAlign(CENTER, CENTER);
  if (coin == 0 && a == 0) {
    text("Please Insert Coin", x+(display_width/2), y+(display_height/2));
  } else if (coin > 0 && a == 0) {
    text("Coin "+coin, x+(display_width/2), y+(display_height/2));
  } else if (a == 1) {
    textAlign(LEFT, CENTER);
    text("Change "+change+" Baht\n    10 coin : "+changeCoin[0]+"\n     5  coin : "+changeCoin[1]+"\n     1  coin : "+changeCoin[2], x+(display_width/8), y+(display_height/2));
  }
}

void changeToAdmin_button() {
  fill(0);
  rect(700, 20, 140, 50);
  fill(255);
  textAlign(CENTER, CENTER);
  text("Admin", 770, 45);
  
}


void changeToDisplay_button() {
  fill(0);
  rect(700, 20, 140, 50);
  fill(255);
  textAlign(CENTER, CENTER);
  text("Display", 770, 45);
  
}



void mousePressed() {
  if (page == 1 &&(mouseX >= 700 && mouseX <= 840)&&(mouseY >= 20 && mouseY <= 70)) {
    page = 2;
  }else if (page == 2 && (mouseX >= 700 && mouseX <= 840)&&(mouseY >= 20 && mouseY <= 70)) {
    page = 1;
  }
}

void admin_page() {
  admin_display();
  changeToDisplay_button();
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

void admin_display(){
  int x = 50, y = 50;
  
  int num = 0;
  while(num < productName.length){
    showProductNum(x, y, productNum[num]);
    fill(50);
    textAlign(LEFT,TOP);
    text(productName[num],x+80,y);
    text(soldProduct[num]+"     "+soldValue(num)+" Baht",x+220,y,num);
    //numProduct_control(x+200,y,num);
    y += 50;
    num++;
  }
}

//void numProduct_control(int x,int y,int num){
  //fill(255);
  //rect(x,y,20,20);
  //fill(#FF0000);
  //textAlign(CENTER,CENTER);
  //text("+",x+(20/2),y+(20/2));
  //if(mousePressed && (mouseX >= x && mouseX <= x+20 && mouseY >= y && mouseY <= y+20)){
    //productNum[num]++;
  //}
//}

int soldValue(int num){
  int value = 0;
  value = soldProduct[num]*productPrice[num];
  return value;
}