/*********************************************************************************************************************************************
    
    Create by Thakdanai Khunsaen
    Student ID 5801012610059
    
    
---------- How to use ------------------------------------------------------------------------------------------------------------------------
Display Page
  - หยอดเหรียญ
  - หากจำนวนเหรียญเท่ากับหรือมากกว่าราคาสินค้า ปุ่มซื้อก็จะเป็นสีเขียวเป็นสัญญาณว่าซื้อได้
  - ผู้ซื้อสามารถเลือกซื้อสินค้าได้ตามใจชอบ โดยการกดปุ่มที่อยู่ใต้สินค้านั้นๆ  **สามารถซื้อได้ทีละชิ้น**
  - เมื่อซื้อแล้วสินค้าจะออกมา ผู้ซื้อต้องเก็บสินค้าออกมาก่อน ไม่เช่นนั้นจะไม่สามารถหยอดเหรียญได้
  - เมื่อเก็บสินค้าแล้ว เครื่องก็จะกลับสู่สถานะพร้อมใช้งาน
  - หากผู้ซื้อไม่สนใจซื้อสินค้าก็สามารถกดที่ปุ่ม CANCEL เพื่อรับเงินคืนได้ หลังจากนั้นก็กดปุ่มอะไรก็ได้บนแป้นพิมพ์เพื่อให้เครื่องกลับสู่สถานะพร้อมใช้งาน
  
Admin Page(การจะเข้าหน้า Admin ต้องไม่มีเหรียญหยอดค้างอยู่)
  - สามารถเพิ่ม/ลดจำนวนสินค้าได้
  - สามารถเพิ่ม/ลดจำนวนเหรียญในเครื่องได้  **ปุ่ม Get Coin จะสามารถใช้ได้เมื่อเหรียญนั้นๆมีจำนวนมากกว่า 50 เหรียญ ก็ทำการเก็บเหรียญออกไปแล้วเหลือไว้เพียง 50 เหรียญ**
  - แสดงจำนวนยอดขายของสินค้า
  - แสดงประวัติการซื้อสินค้าโดยจะแสดงชื่อสินค้า ราคา เงินที่หยอด และตังค์ทอน
  
  - ปุ่ม Reset Machine จะทำการรีเซ็ตเฉพาะยอดขายและประวัติการซื้อสินค้าเท่านั้น
  - ปุ่ม Reset Simulator จะรีเซ็ตทุกอย่างกลับสู่ค่าเริ่มต้น
  
Error
  กรณีที่ 1 : หากตู้ไม่มีเหรียญเพียงพอที่จะทอน ที่หน้าจอ Status Display ก็จะแสดงข้อความ "Error Not Enough Coin"
  กรณีที่ 2 : หากมีเหรียญหยอดค้างอยู่แล้วกดปุ่มเข้าหน้า Admin ที่หน้าจอ Status Display ก็จะแสดงข้อความ "Please Press Calcel"

  วิธีแก้ Error : ให้กดที่ปุ่ม CANCEL(เครื่องจะทำการทอนเหรียญที่หยอดไปออกมา)
  
หมายเหตุ
  - ตู้สามารถเก็บเหรียญได้เหรียญละ 200 เหรียญ หากเหรียญนั้นๆเต็มก็จะไม่สามารถหยอดเหรียญนั้นได้
  - ตู้จะทอนเหรียญที่มีค่ามากกว่าก่อน หากหยอดเหรียญห้าไปสองเหรียญ ตู้ก็จะทอนเหรียญสิบมาหนึ่งเหรียญแทน
  - หากเหรียญสิบหมดตู้จะทอนเหรียญห้าแทน และหากเหรียญห้าหมดตู้ก็จะทอนเป็นเหรียญบาทแทน

*********************************************************************************************************************************************/


int coin = 0;
int[] coinInsert = {0, 0, 0};
/**** Array of Product **********************************************************************************************************************/
//  Product index 0 = Math 1
//  Product index 1 = ProFun
//  Product index 2 = Physic 1
//  Product index 3 = Man & Society
//  Product index 4 = English
String[] productName = {"Math 1", "ProFun", "Physic 1", "Man & Society", "English"};
int[] productColor = {#FFFF55, #FF4444, #FF55FF, #88FF88, #5555FF};
int[] productNum = {50, 50, 50, 50, 50};
int[] productPrice = {59, 70, 45, 39, 45};
/*******************************************************************************************************************************************/

int product_in_outletBox = 0;
String productName_in_outletBox = "";
int productColor_in_outletBox = 0;

int[] soldProduct = {0, 0, 0, 0, 0};

int changeValue = 0;
int[] changeCoin = {0, 0, 0};
int[] coinValue = {1, 5, 10};
int[] coinNum = {50, 50, 50};

int history = 0;
String[] history_name = {"", "", "", "", "", ""};
int[] history_price = {0, 0, 0, 0, 0, 0};
int[] history_insertCoin = {0, 0, 0, 0, 0, 0};
int[] history_change = {0, 0, 0, 0, 0, 0};

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
}

//function(positionX,positionY)
void display_page() {
  changePage(760, 10, page);
  vendingMachine(20, 15);
  coin(720, 270);
}
void admin_page() {
  changePage(760, 10, page);
  productNum_control(20, 20);
  sold(320, 20);
  coin_control(20, 270);
  history(320, 250);
  resetButton(700, 275);
}

//Change Page Function
void changePage(int x, int y, int pageNum) {
  String[] text = {"Admin", "Display"};
  controlButton(x, y, 80, 30, 5, 3, #4444DD, text[pageNum-1], 255);
}

/*********************************************************************************** Display Page ***********************************************************************************/

//Insert Coin Button Function
void coin(int x, int y) {
  int num = 0;
  int buttonColor;
  fill(50);
  textAlign(CENTER, CENTER);
  text("Insert Coin", x+50, y);
  while (num < coinValue.length) {
    if (coinNum[num] < 200) {
      buttonColor = 255;
    } else {
      buttonColor = #FF0000;
    }
    controlButton(x, y+25, 100, 40, 20, 3, buttonColor, str(coinValue[num]), 0);
    y += 50;
    num++;
  }
}

//Vending Machine Function
void vendingMachine(int x, int y) {
  int machineWidth = 670, machineHeight = 420;
  fill(#668D66);
  rect(x, y, machineWidth, machineHeight);

  int productNum_posX = x+55, productNum_posY = y+20;
  int product_posX = x+20, product_posY = y+50;
  int buttonX = x+45, buttonY = y+200;
  int product = 0;
  while (product < productName.length) {
    showProductNum(productNum_posX, productNum_posY, productNum[product]);
    fill(productColor[product]);
    showProduct(product_posX, product_posY, productName[product]);    
    buttonBuy(buttonX, buttonY, product);
    product++;
    product_posX += 130;
    buttonX += 130;
    productNum_posX += 130;
  }
  outletBox(x+20, y+250);
  insertcoin_box(x+270, y+250);
  statusDisplay(x+430, y+250);
  fill(255);
  text("You shall not", x+530, y+385);
  fill(#DD0000);
  text("\"F\"", x+595, y+385);
}

//Show Number of Product Function
void showProductNum(int x, int y, int productNum) {
  int productNum_width = 40, productNum_height = 20;
  strokeWeight(3);
  fill(0);
  rect(x, y, productNum_width, productNum_height);
  fill(#00DD00);
  textAlign(CENTER, CENTER);
  text(productNum, x+(productNum_width/2), y+(productNum_height/2.5));
}

//Show Product Function
void showProduct(int x, int y, String productName) { 
  int productWidth = 110, productHeight = 140;
  strokeWeight(3);
  rect(x, y, productWidth, productHeight);
  fill(255);
  rect(x, y+(productHeight/5.5), productWidth, productHeight/4);
  fill(0);
  textAlign(CENTER);
  text(productName, x+(productWidth/2), y+(productHeight/3));
}

//Buy Button Function
void buttonBuy(int x, int y, int i) {
  int buttonWidth = 60, buttonHeight = 30;
  int buttonColor;
  int available = #00FF00;
  int unavailable = 255;

  if (coin>=productPrice[i] && productNum[i] > 0 && product_in_outletBox == 0 && error == 0 && cancel == 0) {
    buttonColor = available;
  } else {
    buttonColor = unavailable;
  }
  controlButton(x, y, buttonWidth, buttonHeight, 50, 3, buttonColor, str(productPrice[i]), 0);

  if (cancel == 0 && error == 0 && coin >= productPrice[i] && productNum[i] > 0 && mousePressed && buttonPosition(x, y, buttonWidth, buttonHeight)) {
    changeValue = coin;
    history_insertCoin[history] = coin;
    changeValue -= productPrice[i];
    history_change[history] = changeValue;
    change_cal(changeValue);

    if (error == 0) {
      coinNum[0] -= changeCoin[0];
      coinNum[1] -= changeCoin[1];
      coinNum[2] -= changeCoin[2];
      productNum[i] -= 1;
      soldProduct[i] += 1;
      product_in_outletBox = 1;
      productName_in_outletBox = productName[i];
      productColor_in_outletBox = productColor[i];
      history_name[history] = productName[i];
      history_price[history] = productPrice[i];
      history += 1;
      if (history >= 6) {
        int num = 0;
        while (num < history_name.length-1) {
          history_name[num] = history_name[num+1];
          history_price[num] = history_price[num+1];
          history_insertCoin[num] = history_insertCoin[num+1];
          history_change[num] = history_change[num+1];
          num++;
        }
        history = 5;
      }
      coin = 0;
    }
  }
}

//Outlet Box Function
void outletBox(int x, int y) {
  int outletBoxWidth = 240, outletBoxHeight = 150;
  fill(0, 200);
  rect(x, y, outletBoxWidth, outletBoxHeight);
  if (product_in_outletBox ==1 ) {
    fill(productColor_in_outletBox);
    showProduct(x+65, y+5, productName_in_outletBox);
    if (mousePressed && buttonPosition(x, y, outletBoxWidth, outletBoxHeight)) {
      product_in_outletBox = 0;
      for(int i = 0; i< coinInsert.length;i++){
        coinInsert[i] = 0;
      }
    }
  }
}

//Picture of Insert Coin Box & Cancel Button Function
void insertcoin_box(int x, int y) {
  int boxWidth = 150, boxHeight = 150;
  strokeWeight(3);
  fill(100);
  rect(x, y, boxWidth, boxHeight);
  ellipse(x+35, y+40, boxWidth/3, boxHeight/3);
  fill(0);
  rect(x+33, y+20, 4, (boxHeight/3)-10);
  controlButton(x+70, y+20, 70, 40, 4, 0, #FF0000, "CANCEL", 255);
  fill(#88FFFF);
  rect(x+10, y+75, boxWidth-20, boxHeight-85);
}

//Show status on Status Display Function
void statusDisplay(int x, int y) {
  int display_width = 220, display_height = 120;
  fill(0);
  strokeWeight(0);
  rect(x, y, display_width, display_height);
  fill(255);
  textAlign(CENTER, CENTER);
  if (coin == 0 && cancel == 0 && product_in_outletBox == 0 && error == 0) {
    text("Please Insert Coin", x+(display_width/2), y+(display_height/2));
  } else if (coin > 0 && cancel == 0 && error == 0 &&  product_in_outletBox == 0) {
    text("Coin "+coin+"\n10 Coin "+coinInsert[2]+"\n5 Coin "+coinInsert[1]+"\n1 Coin "+coinInsert[0], x+(display_width/2), y+(display_height/2));
  } else if (product_in_outletBox == 1) {
    text("Change "+changeValue+" Baht\n| 10 coin : "+changeCoin[2]+" | 5  coin : "+changeCoin[1]+" |\n| 1  coin : "+changeCoin[0]+" |", x+(display_width/2), y+(display_height/3));
    fill(#FFFF00);
    text("Please Get Product !!!", x+(display_width/2), y+(display_height-20));
  } else if (error == 1 && cancel == 0) {
    fill(#FF0000);
    text("Error !!\nNot Enough Coin", x+(display_width/2), y+(display_height/2));
  } else if (error == 2) {
    fill(#FF0000);
    text("Please Press CANCEL", x+(display_width/2), y+(display_height/2));
  } else if (cancel == 1) {
    text("Change "+changeValue+" Baht\n| 10 coin : "+changeCoin[2]+" | 5  coin : "+changeCoin[1]+" |\n| 1  coin : "+changeCoin[0]+" |", x+(display_width/2), y+(display_height/3));
    fill(#FFFF00);
    text("Please Press Any Key", x+(display_width/2), y+(display_height-20));
  }
}
/************************************************************************************************************************************************************************************/

/************************************************************************************ Admin Page ************************************************************************************/

//Reset Button Function
void resetButton(int x, int y) {
  int buttonWidth = 140, buttonHeight = 50;
  controlButton(x, y, buttonWidth, buttonHeight, 8, 0, #FFDD00, "Reset\nMachine", 0);
  controlButton(x, y+75, buttonWidth, buttonHeight, 8, 0, #FF0000, "Reset\nSimulator", 255);
}

//Number of Product Control
void productNum_control(int x, int y) {
  int num = 0;
  fill(0);
  textAlign(CENTER, TOP);
  text("Number of Product", x+115, y);
  y += 30;
  while (num < productNum.length) {
    controlButton(x, y, 60, 20, 4, 0, 255, "Empty", #FF0000);
    controlButton(x+65, y, 20, 20, 4, 0, 255, "-", #FF0000);
    showProductNum(x+95, y, productNum[num]);
    controlButton(x+145, y, 20, 20, 4, 0, 255, "+", #008800);
    controlButton(x+170, y, 60, 20, 4, 0, 255, "Full", #008800);    
    y += 35;
    num++;
  }
  controlButton(x, y, 90, 30, 4, 0, #FF0000, "Empty All", 255);
  controlButton(x+140, y, 90, 30, 4, 0, #008800, "Full All", 255);
}

//Show Product Sold Function
void sold(int x, int y) {
  fill(0);
  textAlign(CENTER, TOP);
  text("Product Name", x, y);
  text("Price", x+100, y);
  text("Sold", x+200, y);
  text("Value", x+300, y);
  y += 30;

  int num = 0;
  while (num < productName.length) {
    fill(50);
    textAlign(LEFT, TOP);
    text(productName[num], x-50, y);
    text(productPrice[num]+" Baht", x+75, y);
    textAlign(RIGHT, TOP);
    text(soldProduct[num], x+215, y);
    text(soldProduct[num]*productPrice[num]+" Baht", x+350, y);
    y += 35;
    num++;
  }
}

//Number of Coin Control Function
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
    controlButton(x+150, y, 20, 20, 4, 0, #FF8888, "-", #FF0000);
    controlButton(x+175, y, 20, 20, 4, 0, #448844, "+", #00FF00);
    controlButton(x+200, y, 70, 20, 4, 0, #FFFF99, str(coinValue[coinType]*coinNum[coinType]), #FF8800);
    value += coinValue[coinType]*coinNum[coinType];
    y += 30;
    coinType++;
  }
  fill(#880088);
  textAlign(LEFT, TOP);
  text("Value = "+value+" Baht", x, y);
}

//Show History Function
void history(int x, int y) {
  int historyBox_width = 350, historyBox_height = 135;
  fill(255);
  rect(x, y+20, historyBox_width, historyBox_height);
  fill(0);
  text("History", x, y);
  text("Product", x+(historyBox_width/20), y+25);
  text("Price", x+(historyBox_width/2.5), y+25);
  text("Coin", x+(historyBox_width/1.65), y+25);
  text("Change", x+(historyBox_width/1.25), y+25);
  int textY = y+50;
  int count = 0;
  while (count < history) {
    fill(#004400);
    textAlign(LEFT, TOP);
    text(history_name[count], x+(historyBox_width/20), textY);
    textAlign(CENTER, TOP);
    text(history_price[count], x+(historyBox_width/2.2), textY);
    text(history_insertCoin[count], x+(historyBox_width/1.52), textY);
    text(history_change[count], x+(historyBox_width/1.12), textY);
    textY += 20;
    count++;
  }
}
/***********************************************************************************************************************************************************************************/


void mousePressed() {
  /********************************************************************************** Change Page **********************************************************************************/
  if (buttonPosition(760, 10, 80, 30)) {
    if (page == 1  && error == 0 && coin == 0) {
      page = 2;
    } else if (coin > 0 && cancel == 0) {
      error = 2;
    } else if (page == 2) {
      page = 1;
    }
  }
  /********************************************************************************************************************************************************************************/

  if (page==1) {
    /***************************************************************************** Insert Coin Button *****************************************************************************/
    int coinType_insert = 0;
    int insertcoin_posY = 295;
    while (coinType_insert < coinValue.length) {
      if (cancel == 0 && error == 0 && coinNum[coinType_insert] < 200 && product_in_outletBox == 0 && buttonPosition(720, insertcoin_posY, 100, 40)) {
        coin += coinValue[coinType_insert];
        coinNum[coinType_insert] += 1;
        coinInsert[coinType_insert] += 1;
      }
      insertcoin_posY += 50;
      coinType_insert++;
    }
    /******************************************************************************************************************************************************************************/

    /*********************************************************************************** Cancel ***********************************************************************************/
    if (cancel == 0 && coin > 0 && buttonPosition(360, 285, 70, 40)) {
      for(int i = 0; i< coinInsert.length;i++){
        coinInsert[i] = 0;
      }
      cancel = 1;
      changeValue = coin;
      history_name[history] = "CANCEL";
      history_price[history] = 0;
      history_insertCoin[history] = coin;
      history_change[history] = coin;
      history +=1 ;
      if (history > 5) {
        int num = 0;
        while (num < history_name.length-1) {
          history_name[num] = history_name[num+1];
          history_price[num] = history_price[num+1];
          history_insertCoin[num] = history_insertCoin[num+1];
          history_change[num] = history_change[num+1];
          num++;
        }
        history = 5;
      }
      change_cal(changeValue);
      coinNum[0] -= changeCoin[0];
      coinNum[1] -= changeCoin[1];
      coinNum[2] -= changeCoin[2];
      coin = 0;
      error = 0;
    }
    /*******************************************************************************************************************************************************************************/
  }

  if (page == 2) {
    /************************************************************************** Number of Product Control **************************************************************************/
    int product = 0;
    int numControl_buttonY = 50;
    while (product < productNum.length) {
      if (buttonPosition(20, numControl_buttonY, 60, 20)) {
        productNum[product] = 0;
      } else if (buttonPosition(85, numControl_buttonY, 20, 20)) {
        productNum[product] -= 1;
        if (productNum[product] <= 0) {
          productNum[product] = 0;
        }
      } else if (buttonPosition(165, numControl_buttonY, 20, 20)) {
        productNum[product] += 1;
        if (productNum[product] >= 100) {
          productNum[product] = 100;
        }
      } else if (buttonPosition(190, numControl_buttonY, 60, 20)) {
        productNum[product] = 100;
      }
      numControl_buttonY += 35;
      product++;
    }
    int[] empty = {0, 0, 0, 0, 0};
    int[] full = {100, 100, 100, 100, 100};
    if (buttonPosition(20, numControl_buttonY, 90, 30)) {
      productNum = empty;
    } else if (buttonPosition(160, numControl_buttonY, 90, 30)) {
      productNum = full;
    } 
    /******************************************************************************************************************************************************************************/

    /*************************************************************************** Number of Coin Control ***************************************************************************/
    int coinType_control = 0;
    int coinControl_buttonY = 300;
    while (coinType_control < coinValue.length) {
      if (buttonPosition(185, coinControl_buttonY, 20, 20)) {
        coinNum[coinType_control] -= 1;
        if (coinNum[coinType_control] <= 0) {
          coinNum[coinType_control] = 0;
        }
      } else if (buttonPosition(210, coinControl_buttonY, 20, 20)) {
        coinNum[coinType_control] += 1;
        if (coinNum[coinType_control] >= 200) {
          coinNum[coinType_control] = 200;
        }
      } else if (coinNum[coinType_control] > 50 && buttonPosition(235, coinControl_buttonY, 70, 20)) {
        coinNum[coinType_control] = 50;
      }
      coinControl_buttonY += 30;
      coinType_control++;
    }
    /*****************************************************************************************************************************************************************************/

    int[] sold_default = {0, 0, 0, 0, 0};
    int[] productNum_default = {50, 50, 50, 50, 50};
    int[] coinNum_default = {50, 50, 50};
    /******************************************************************************* Reset Machine *******************************************************************************/
    if (buttonPosition(700, 275, 140, 50)) {
      soldProduct = sold_default;
      for(int i = 0; i< coinInsert.length;i++){
        coinInsert[i] = 0;
      }
      coin = 0;
      history = 0;
      error = 0;
      cancel = 0;
    }
    /*****************************************************************************************************************************************************************************/

    /****************************************************************************** Reset Simulator ******************************************************************************/
    if (buttonPosition(700, 350, 140, 50)) {
      soldProduct = sold_default;
      productNum = productNum_default;
      coinNum = coinNum_default;
      for(int i = 0; i< coinInsert.length;i++){
        coinInsert[i] = 0;
      }
      coin = 0;
      history = 0;
      error = 0;
      cancel = 0;
      page = 1;
    }
    /*****************************************************************************************************************************************************************************/
  }
}

/********************************************************************************** Clear Cancel **********************************************************************************/
void keyPressed() {
  if (page == 1 && cancel == 1) {
    error = 0;
    cancel = 0;
    coin = 0;
  }
}
/**********************************************************************************************************************************************************************************/

/********************************************************************************* Other Function *********************************************************************************/

//Create Button Function
void controlButton(int x, int y, int buttonWidth, int buttonHeight, int corner, int stroke, int buttonColor, String text, int textColor) {
  strokeWeight(stroke);
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, corner);
  fill(textColor);
  textAlign(CENTER, CENTER);
  text(text, x+(buttonWidth/2), y+(buttonHeight/2.5));
}

//Button Position Function
boolean buttonPosition(int x, int y, int buttonWidth, int buttonHeight) {
  if (mouseX >= x && mouseX <= x+buttonWidth && mouseY >= y && mouseY <= y+buttonHeight) {
    return true;
  } else {
    return false;
  }
}

//Change Coin Calculator
void change_cal(int change) {
  if (coinNum[0] < (change%10)%5) {
    error = 1;
  } else if (coinNum[2] >= change/10) {
    if (coinNum[1] >= (change%10)/5) {
      changeCoin[0] = (change%10)%5;
      changeCoin[1] = (change%10)/5;
      changeCoin[2] = change/10;
    } else {
      changeCoin[2] = change/10;
      changeCoin[1] = coinNum[1];
      change -= (changeCoin[2]*coinValue[2])+(changeCoin[1]*coinValue[1]);
      changeCoin[0] = change;
    }
  } else if (coinNum[2] < change/10) {
    if (coinNum[1] >= change/5) {
      changeCoin[2] = coinNum[2];
      change -= changeCoin[2]*coinValue[2];
      changeCoin[1] = change/5;
      changeCoin[0] = change%5;
    } else {
      changeCoin[2] = coinNum[2];
      changeCoin[1] = coinNum[1];
      change -= (changeCoin[2]*coinValue[2])+(changeCoin[1]*coinValue[1]);
      changeCoin[0] = change;
    }
  }
  int coinType = 0;
  while (coinType < coinValue.length) {
    if (coinNum[coinType] < changeCoin[coinType]) {
      error = 1;
    }
    coinType++;
  }
}
/**********************************************************************************************************************************************************************************/