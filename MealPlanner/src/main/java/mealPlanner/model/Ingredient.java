package mealPlanner.model;

import java.util.*;


public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;

  //Ingredient Associations
  private List<OwnedIngredient> ownedIngredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName)
  {
    name = aName;
    ownedIngredients = new ArrayList<OwnedIngredient>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public OwnedIngredient getOwnedIngredient(int index)
  {
    OwnedIngredient aOwnedIngredient = ownedIngredients.get(index);
    return aOwnedIngredient;
  }

  public List<OwnedIngredient> getOwnedIngredients()
  {
    List<OwnedIngredient> newOwnedIngredients = Collections.unmodifiableList(ownedIngredients);
    return newOwnedIngredients;
  }

  public int numberOfOwnedIngredients()
  {
    int number = ownedIngredients.size();
    return number;
  }

  public boolean hasOwnedIngredients()
  {
    boolean has = ownedIngredients.size() > 0;
    return has;
  }

  public int indexOfOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    int index = ownedIngredients.indexOf(aOwnedIngredient);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwnedIngredients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OwnedIngredient addOwnedIngredient(int aAmount, User aUser)
  {
    return new OwnedIngredient(aAmount, this, aUser);
  }

  public boolean addOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    boolean wasAdded = false;
    if (ownedIngredients.contains(aOwnedIngredient)) { return false; }
    Ingredient existingIngredient = aOwnedIngredient.getIngredient();
    boolean isNewIngredient = existingIngredient != null && !this.equals(existingIngredient);
    if (isNewIngredient)
    {
      aOwnedIngredient.setIngredient(this);
    }
    else
    {
      ownedIngredients.add(aOwnedIngredient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOwnedIngredient(OwnedIngredient aOwnedIngredient)
  {
    boolean wasRemoved = false;
    //Unable to remove aOwnedIngredient, as it must always have a ingredient
    if (!this.equals(aOwnedIngredient.getIngredient()))
    {
      ownedIngredients.remove(aOwnedIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnedIngredientAt(OwnedIngredient aOwnedIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addOwnedIngredient(aOwnedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnedIngredients()) { index = numberOfOwnedIngredients() - 1; }
      ownedIngredients.remove(aOwnedIngredient);
      ownedIngredients.add(index, aOwnedIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnedIngredientAt(OwnedIngredient aOwnedIngredient, int index)
  {
    boolean wasAdded = false;
    if(ownedIngredients.contains(aOwnedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnedIngredients()) { index = numberOfOwnedIngredients() - 1; }
      ownedIngredients.remove(aOwnedIngredient);
      ownedIngredients.add(index, aOwnedIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnedIngredientAt(aOwnedIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=ownedIngredients.size(); i > 0; i--)
    {
      OwnedIngredient aOwnedIngredient = ownedIngredients.get(i - 1);
      aOwnedIngredient.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}