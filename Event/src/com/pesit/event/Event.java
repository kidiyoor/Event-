package com.pesit.event;

public class Event {
    public String icon;
    public String title;
    public String org;
    public String date;
    public String description;
    public String venue;
    public String loc;
    String url;
    public Event(){
        super();
    }
    
    public Event(String icon, String title, String org,String date,String description,String venue,String loc,String url) {
        super();
        this.icon = icon;
        this.title = title;
        this.org=org;
        this.date=date;
        this.description=description;
        this.venue=venue;
        this.loc=loc;
        this.url=url;
    }
    public String getUrl()
    {
    	return icon;
    }
}
