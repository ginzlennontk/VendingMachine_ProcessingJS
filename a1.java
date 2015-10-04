int coin = 0;

String[] productName = {"Math 1", "ProFun", "Physic 1", "Man & Society", "English"};
int product_in_outletBox = 0;
int[] productNum = {4, 2, 5, 5, 1};
int[] productPrice = {59, 70, 45, 39, 45};

int[] soldProduct = {0, 0, 0, 0, 0};

int changeValue = 0;
int[] changeCoin = {0, 0, 0};
int[] coinValue = {1, 5, 10};
int[] coinNum = {10, 10, 10};

int cancel = 0;
int error = 0;
int page = 1;

void setup() {
  size(850, 450);
}

void draw() {
  background(#DDFFFF);
  textSize(15);

  if (page == 1) {
    display_page();
  } else if (page == 2) {
    admin_page();
  }

  text(pmouseX+","+pmouseY, mouseX, mouseY);
}



void display_page() {
  vendingMachine(20, 15);
  changePage(page);
  coin(295);
}

void admin_page() {
  admin_display();
  changePage(page);
}
void coin(int y) {
  int num = 0;
  while (num < coinValue.length) {
    if (coinNum[num] >= 200) {
      fill(#FF0000);
    } else {
      fill(255);
    }
    rect(720, y, 100, 40, 20);
    fill(0);
    textAlign(CENTER, CENTER);
    text(coinValue[num], 720+(100/2), y+(40/2));
    y += 50;
    num++;
  }
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
    product++;
    product_posX += 130;
    buttonX += 130;
    productNum_posX += 130;
  }
  outletBox(x+20, y+250);
  insertcoin_box(x+280, y+250);
  statusDisplay(x+450, y+250);
  //text("You shall not"
}



void showProductNum(int x, int y, int productNum) {
  //int y = 70;
  int productNum_width = 40, productNum_height = 20;
  strokeWeight(3);
  fill(0);
  rect(x, y, productNum_width, productNum_height);
  fill(#00DD00);
  textAlign(CENTER, CENTER);
  text(productNum, x+(productNum_width/2), y+(productNum_height/2.5));
}



void showProduct(int x, int y, String productName) { 
  //int y = 120;
  final int productWidth = 110, productHeight = 140;
  fill(255);
  strokeWeight(3);
  rect(x, y, productWidth, productHeight);
  fill(0);
  textAlign(CENTER);
  text(productName, x+(productWidth/2), y+(productHeight/3));
}




void buttonBuy(int x, int y, int i) {
  final int buttonWidth = 60, buttonHeight = 30;
  int buttonColor;
  int available = #00FF00;
  int unavailable = 255;

  if (coin>=productPrice[i] && productNum[i] > 0 && product_in_outletBox == 0 && error == 0) {
    buttonColor = available;
  } else {
    buttonColor = unavailable;
  }

  strokeWeight(3);
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, 50);
  fill(0);
  textAlign(CENTER, CENTER);
  text(productPrice[i], x+(buttonWidth/2), y+(buttonHeight/2.5));

  if (error != 2 && product_in_outletBox == 0 && coin >= productPrice[i] && productNum[i] > 0 && mousePressed && (mouseX >= x && mouseX <= x+buttonWidth) && (mouseY >= y && mouseY <= y+buttonHeight)) {
    changeValue = coin;
    changeValue -= productPrice[i];
    change_cal(changeValue);
    int coinType = 0;
    while (coinType < coinValue.length) {
      if (coinNum[coinType] < changeCoin[coinType]) {
        error = 1;
      }
      coinType++;
    }
    if (error == 0) {
      coinNum[0] -= changeCoin[0];
      coinNum[1] -= changeCoin[1];
      coinNum[2] -= changeCoin[2];
      productNum[i] -= 1;
      soldProduct[i] += 1;
      product_in_outletBox = 1;
      coin = 0;
    }
  }
}
void change_cal(int change) {
  if (coinNum[0] < (change%10)%5) {
    error = 1;
  } else if (coinNum[2] > change/10) {
    if (coinNum[1] > (change%10)/5) {
      changeCoin[0] = (change%10)%5;
      changeCoin[1] = (change%10)/5;
      changeCoin[2] = change/10;
    } else if (coinNum[1] < (change%10)/5) {
      changeCoin[2] = change/10;
      changeCoin[1] = coinNum[1];
      change -= (changeCoin[2]*coinValue[2])+(changeCoin[1]*coinValue[1]);

      changeCoin[0] = change;
    }
  } else if (coinNum[2] < change/10) {
    if (coinNum[1] > change/5) {
      changeCoin[2] = coinNum[2];
      change -= changeCoin[2]*coinValue[2];
      changeCoin[1] = change/5;
      changeCoin[0] = change%5;
    } else if (coinNum[1] < change/5) {
      changeCoin[2] = coinNum[2];
      changeCoin[1] = coinNum[1];
      change -= (changeCoin[2]*coinValue[2])+(changeCoin[1]*coinValue[1]);
      changeCoin[0] = change;
    }
  } else if (change <= 10) {
    if (change >= 5 && coinNum[1] > 0) {
      changeCoin[2] = 0;
      changeCoin[1] = 1;
      changeCoin[0] = change%5;
    } else { 
      changeCoin[2] = 0;
      changeCoin[1] = 0;
      changeCoin[0] = change;
    }
  }
}




void outletBox(int x, int y) {
  fill(0, 200);
  rect(x, y, 240, 150);
  if (error != 2 && product_in_outletBox == 1 && mousePressed && (mouseX >= x && mouseX <= x+240) && (mouseY >= y && mouseY <= y+150)) {
    product_in_outletBox = 0;
  }
  if (product_in_outletBox ==1 ) {
    product_in_outletBox = 1;
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

void insertcoin_box(int x, int y) {
  fill(100);
  rect(x, y, 150, 150);
  ellipse(x+35, y+40, 50, 50);
  fill(0);
  rect(x+33, y+20, 4, 40);
  controlButton(x+70, y+20, 70, 40, #FF0000, "CANCEL", 255);
  fill(#88FFFF);
  rect(x+10, y+75, 130, 65);
}


void statusDisplay(int x, int y) {
  int display_width = 200, display_height = 120;
  fill(0);
  strokeWeight(0);
  rect(x, y, display_width, display_height);
  fill(255);
  textAlign(CENTER, CENTER);
  if (coin == 0 && cancel == 0 && product_in_outletBox == 0 && error == 0) {
    text("Please Insert Coin", x+(display_width/2), y+(display_height/2));
  } else if (coin > 0 && cancel == 0 && error == 0 &&  product_in_outletBox == 0) {
    text("Coin "+coin, x+(display_width/2), y+(display_height/2));
  } else if (product_in_outletBox == 1) {
    text("Change "+changeValue+" Baht\n| 10 coin : "+changeCoin[2]+" | 5  coin : "+changeCoin[1]+" |\n| 1  coin : "+changeCoin[0]+" |", x+(display_width/2), y+(display_height/3));
    fill(#FFFF00);
    text("Please Get Product !!!", x+(display_width/2), y+(display_height-20));
  } else if (error == 1 && cancel == 0) {
    fill(#FF0000);
    text("Error !!\nNot Enough Coin", x+(display_width/2), y+(display_height/2));
  } else if (error == 2) {
    fill(#FF0000);
    text("Error !!\nLen Here Arai Isus!!", x+(display_width/2), y+(display_height/2));
  } else if (cancel == 1) {
    text("Change "+changeValue+" Baht\n| 10 coin : "+changeCoin[2]+" | 5  coin : "+changeCoin[1]+" |\n| 1  coin : "+changeCoin[0]+" |", x+(display_width/2), y+(display_height/3));
    fill(#FFFF00);
    text("Please Press Any Key", x+(display_width/2), y+(display_height-20));
  }
}


void mousePressed() {
  /****************************************** Change Page ******************************************/
  if (page == 1 &&(mouseX >= 700 && mouseX <= 840)&&(mouseY >= 20 && mouseY <= 70)) {
    page = 2;
  } else if (page == 2 && (mouseX >= 700 && mouseX <= 840)&&(mouseY >= 20 && mouseY <= 70)) {
    page = 1;
  }
  /************************************************************************************************/
  if (page==1) {
    if (error != 2) {
      /******************************************* Insert Coin Button *******************************************/
      int coinType_insert = 0;
      int insertcoin_posY = 295;
      while (coinType_insert < coinValue.length) {
        if (error == 0 && coinNum[coinType_insert] < 200 && product_in_outletBox == 0 && mouseX >= 700 && mouseX <= 840 && mouseY >= insertcoin_posY && mouseY <= insertcoin_posY+40) {
          coin += coinValue[coinType_insert];
          coinNum[coinType_insert] += 1;
        }
        insertcoin_posY += 50;
        coinType_insert++;
      }
      /*********************************************************************************************************/

      /******************************************** Cancel ********************************************/
      if (cancel == 0 && coin > 0 && mouseX >= 370 && mouseX <= 440 && mouseY >= 285 && mouseY <= 325) {
        cancel = 1;
        changeValue = coin;
        change_cal(changeValue);
        coinNum[0] -= changeCoin[0];
        coinNum[1] -= changeCoin[1];
        coinNum[2] -= changeCoin[2];
        int coinType_error = 0;
        while (coinType_error < coinValue.length) {
          if (coinNum[coinType_error] < 0) {
            error = 2;
          }
          coinType_error++;
        }
      }
      /**********************************************************************************************/
    }
  }
  if (page == 2) {
    if (error != 2) {
      /***************************************************** Number of Product Control *****************************************************/
      int product = 0;
      int numControl_buttonY = 50;
      while (product < productNum.length) {
        if (mouseX >= 20 && mouseX <= 80 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+20) {
          productNum[product] = 0;
        } else if (mouseX >= 85 && mouseX <= 105 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+20) {
          productNum[product] -= 1;
          if (productNum[product] <= 0) {
            productNum[product] = 0;
          }
        } else if (mouseX >= 165 && mouseX <= 185 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+20) {
          productNum[product] += 1;
          if (productNum[product] >= 100) {
            productNum[product] = 100;
          }
        } else if (mouseX >= 190 && mouseX <= 250 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+20) {
          productNum[product] = 100;
        }
        numControl_buttonY += 35;
        product++;
      }
      int[] empty = {0, 0, 0, 0, 0};
      int[] full = {100, 100, 100, 100, 100};
      if (mouseX >= 20 && mouseX <= 110 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+30) {
        productNum = empty;
      } else if (mouseX >= 160 && mouseX <= 250 && mouseY >= numControl_buttonY && mouseY <= numControl_buttonY+30) {
        productNum = full;
      } 
      /*************************************************************************************************************************************/

      /********************************************************* Number of Coin Control *********************************************************/
      int coinType_control = 0;
      int coinControl_buttonY = 300;
      while (coinType_control < coinValue.length) {
        if (mouseX >= 185 && mouseX <= 205 && mouseY >= coinControl_buttonY && mouseY <= coinControl_buttonY+20) {
          coinNum[coinType_control] -= 1;
          if (coinNum[coinType_control] <= 0) {
            coinNum[coinType_control] = 0;
          }
        } else if (mouseX >= 210 && mouseX <= 230 && mouseY >= coinControl_buttonY && mouseY <= coinControl_buttonY+20) {
          coinNum[coinType_control] += 1;
          if (coinNum[coinType_control] >= 200) {
            coinNum[coinType_control] = 200;
          }
        } else if (coinNum[coinType_control] > 50 && mouseX >= 235 && mouseX <= 305 && mouseY >= coinControl_buttonY && mouseY <= coinControl_buttonY+20) {
          coinNum[coinType_control] = 50;
        }
        coinControl_buttonY += 30;
        coinType_control++;
      }
      /*****************************************************************************************************************************************/
    }
  }
}
void keyPressed() {
  if (cancel == 1 && error != 2) {
    error = 0;
    cancel = 0;
    coin = 0;
  }
}
void changePage(int pageNum) {
  String[] text = {"Admin", "Display"};
  strokeWeight(3);
  fill(0);
  rect(700, 20, 140, 50);
  fill(255);
  textAlign(CENTER, CENTER);
  text(text[pageNum-1], 770, 45);
}


void admin_display() {
  int x = 20, y = 20;
  productNum_control(x, y);
  coin_control(x, y+250);
}


void productNum_control(int x, int y) {
  int num = 0;
  fill(0);
  textAlign(CENTER, TOP);
  text("Number of Product", x+115, y);
  text("Product Name", x+300, y);
  text("Price", x+400, y);
  text("Sold", x+500, y);
  text("Value", x+600, y);
  y += 30;
  while (num < productName.length) {
    controlButton(x, y, 60, 20, 255, "Empty", #FF0000);
    controlButton(x+65, y, 20, 20, 255, "-", #FF0000);
    showProductNum(x+95, y, productNum[num]);
    controlButton(x+145, y, 20, 20, 255, "+", #008800);
    controlButton(x+170, y, 60, 20, 255, "Full", #008800);    
    fill(50);
    textAlign(LEFT, TOP);
    text(productName[num], x+250, y);
    text(productPrice[num]+" Baht", x+375, y);
    textAlign(RIGHT, TOP);
    text(soldProduct[num], x+515, y);
    text(soldValue(num)+" Baht", x+650, y);
    y += 35;
    num++;
  }
  controlButton(x, y, 90, 30, #FF0000, "Empty All", 255);
  controlButton(x+140, y, 90, 30, #008800, "Full All", 255);
}

void coin_control(int x, int y) {
  int coinType = 0;
  int value = 0;
  fill(0);
  textAlign(LEFT, TOP);
  text("Number of Coin", x, y);
  x+= 15; 
  y += 30;
  while (coinType < coinValue.length) {
    fill(50);
    textAlign(LEFT, TOP);
    text(coinValue[coinType]+" coin : "+coinNum[coinType]+"/200", x, y);
    controlButton(x+150, y, 20, 20, #FF8888, "-", #FF0000);
    controlButton(x+175, y, 20, 20, #448844, "+", #00FF00);
    controlButton(x+200, y, 70, 20, #FFFF99, "Get Coin", #FF8800);
    value += coinValue[coinType]*coinNum[coinType];
    y += 30;
    coinType++;
  }
  fill(#880088);
  textAlign(LEFT, TOP);
  text("Value = "+value+" Baht",x,y);
}

void controlButton(int x, int y, int buttonWidth, int buttonHeight, int buttonColor, String text, int textColor) {
  strokeWeight(0);
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, 4);
  fill(textColor);
  textAlign(CENTER, CENTER);
  text(text, x+(buttonWidth/2), y+(buttonHeight/2.5));
}

int soldValue(int num) {
  int value = 0;
  value = soldProduct[num]*productPrice[num];
  return value;
}