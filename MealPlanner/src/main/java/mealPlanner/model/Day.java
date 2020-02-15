/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/
package mealPlanner.model;

import java.sql.Date;
import java.util.*;

// line 40 "model.ump"
// line 92 "model.ump"
public class Day
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Day Attributes
  private Date date;
  private int calorieCount;

  //Day Associations
  private List<Meal> meals;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Day(Date aDate, int aCalorieCount)
  {
    date = aDate;
    calorieCount = aCalorieCount;
    meals = new ArrayList<Meal>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalorieCount(int aCalorieCount)
  {
    boolean wasSet = false;
    calorieCount = aCalorieCount;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getCalorieCount()
  {
    return calorieCount;
  }
  /* Code from template association_GetMany */
  public Meal getMeal(int index)
  {
    Meal aMeal = meals.get(index);
    return aMeal;
  }

  public List<Meal> getMeals()
  {
    List<Meal> newMeals = Collections.unmodifiableList(meals);
    return newMeals;
  }

  public int numberOfMeals()
  {
    int number = meals.size();
    return number;
  }

  public boolean hasMeals()
  {
    boolean has = meals.size() > 0;
    return has;
  }

  public int indexOfMeal(Meal aMeal)
  {
    int index = meals.indexOf(aMeal);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMeals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Meal addMeal(Recipe aRecipe, int aServings)
  {
    return new Meal(aRecipe, aServings, this);
  }

  public boolean addMeal(Meal aMeal)
  {
    boolean wasAdded = false;
    if (meals.contains(aMeal)) { return false; }
    Day existingDay = aMeal.getDay();
    boolean isNewDay = existingDay != null && !this.equals(existingDay);
    if (isNewDay)
    {
      aMeal.setDay(this);
    }
    else
    {
      meals.add(aMeal);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMeal(Meal aMeal)
  {
    boolean wasRemoved = false;
    //Unable to remove aMeal, as it must always have a day
    if (!this.equals(aMeal.getDay()))
    {
      meals.remove(aMeal);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMealAt(Meal aMeal, int index)
  {  
    boolean wasAdded = false;
    if(addMeal(aMeal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeals()) { index = numberOfMeals() - 1; }
      meals.remove(aMeal);
      meals.add(index, aMeal);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMealAt(Meal aMeal, int index)
  {
    boolean wasAdded = false;
    if(meals.contains(aMeal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeals()) { index = numberOfMeals() - 1; }
      meals.remove(aMeal);
      meals.add(index, aMeal);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMealAt(aMeal, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=meals.size(); i > 0; i--)
    {
      Meal aMeal = meals.get(i - 1);
      aMeal.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calorieCount" + ":" + getCalorieCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}