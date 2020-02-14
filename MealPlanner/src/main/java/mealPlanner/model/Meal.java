package mealPlanner.model;

public class Meal
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Meal Attributes
  private Recipe recipe;
  private int servings;

  //Meal Associations
  private Day day;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Meal(Recipe aRecipe, int aServings, Day aDay)
  {
    recipe = aRecipe;
    servings = aServings;
    boolean didAddDay = setDay(aDay);
    if (!didAddDay)
    {
      throw new RuntimeException("Unable to create meal due to day. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRecipe(Recipe aRecipe)
  {
    boolean wasSet = false;
    recipe = aRecipe;
    wasSet = true;
    return wasSet;
  }

  public boolean setServings(int aServings)
  {
    boolean wasSet = false;
    servings = aServings;
    wasSet = true;
    return wasSet;
  }

  public Recipe getRecipe()
  {
    return recipe;
  }

  public int getServings()
  {
    return servings;
  }
  /* Code from template association_GetOne */
  public Day getDay()
  {
    return day;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDay(Day aDay)
  {
    boolean wasSet = false;
    if (aDay == null)
    {
      return wasSet;
    }

    Day existingDay = day;
    day = aDay;
    if (existingDay != null && !existingDay.equals(aDay))
    {
      existingDay.removeMeal(this);
    }
    day.addMeal(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Day placeholderDay = day;
    this.day = null;
    if(placeholderDay != null)
    {
      placeholderDay.removeMeal(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "servings" + ":" + getServings()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "recipe" + "=" + (getRecipe() != null ? !getRecipe().equals(this)  ? getRecipe().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "day = "+(getDay()!=null?Integer.toHexString(System.identityHashCode(getDay())):"null");
  }
}