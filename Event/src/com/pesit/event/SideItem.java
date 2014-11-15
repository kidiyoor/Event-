package com.pesit.event;

public class SideItem {

    private String title;
    private int icon;

    public SideItem(String title) {
            this.title = title;
           

       }

     public String getTitle() {
          return title;
      }

    public void setTitle(String title) {
         this.title = title;
    }

    public int getIcon() {
        return icon;
  }

    public void setIcon(int icon) {
     this.icon = icon;
   }

}