package Model;

import Controller.Token;
import Model.properties.Property;
import Model.actioncards.AbstractActionCard;
import Model.properties.BuildingTypes;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractPlayer implements Transfer {
    private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    private String name;

    private double funds;
    private ArrayList<Property> properties;
    private ArrayList<AbstractActionCard> actionCards;
    private Token token;
    private String tokenImage;
    private int currentLocation;

    private boolean inJail;

    public AbstractPlayer() {
        this.inJail = false;
        properties = new ArrayList<>();
        actionCards = new ArrayList<>();
    }

    public AbstractPlayer(String name) {
        this.name = name;
//        this.tokenImage = tokenImage;
        this.inJail = false;
        properties = new ArrayList<>();
        actionCards = new ArrayList<>();
    }

    @Override
    public void makePayment(double amount, Transfer receiver) {
        if(this.funds < amount) {
            throw new IllegalArgumentException("Not enough money to pay");
        }
        setFunds(this.funds - amount);
        receiver.receivePayment(amount);
    }

    @Override
    public void receivePayment(double amount) {
        setFunds(this.funds + amount);
    }

    public boolean checkMonopoly() {
        return false;
    }

    public double getFunds() {
        return funds;
    }

    @Deprecated
    public Token getToken() {
        return token;
    }

    public int getCurrentLocation(){
        return currentLocation;
    }

    @Deprecated
    public void setCurrentLocation(int newLocation) {
        currentLocation = newLocation;
    }

    public int moveTo(int newLocation) {
        int oldLocation = currentLocation;
        currentLocation = newLocation;
        myPCS.firePropertyChange("currentLocation",oldLocation,currentLocation);
        return currentLocation;
    }

    public void proposeTrade(AbstractPlayer other) {

    }

    public void build(BuildingTypes type) {


    }

    public abstract void doSpecialMove();

    public void setJail(boolean set) {
        inJail = set;
    }

    public void setFunds(double newFunds) {
        myPCS.firePropertyChange("funds",this.funds,newFunds);
        this.funds = newFunds;
        System.out.println(this.getName() + "'s funds updated. new funds: " + funds);
    }

    @Deprecated
    public void setToken(Token token) {
        this.token = token;
    }

    public void addFunds(double addAmount) {
        setFunds(this.funds += addAmount);
    }

    public boolean isInJail() {
        return inJail;
    }

    public String getName() {
        return this.name;
    }

    @Deprecated
    public String getTokenImage() {
        return this.tokenImage;
    }

    public void addActionCard(AbstractActionCard c) {
        actionCards.add(c);
    }

    public abstract Map<BuildingTypes, Integer> getNumBuildings();

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        myPCS.addPropertyChangeListener(propertyName,listener);
    }

}
