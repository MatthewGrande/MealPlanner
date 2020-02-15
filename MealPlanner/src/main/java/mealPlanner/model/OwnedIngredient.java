/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/

package mealPlanner.model;

// line 52 "model.ump"
// line 102 "model.ump"
public class OwnedIngredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnedIngredient Attributes
  private int amount;

  //OwnedIngredient Associations
  private Ingredient ingredient;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnedIngredient(int aAmount, Ingredient aIngredient, User aUser)
  {
    amount = aAmount;
    if (!setIngredient(aIngredient))
    {
      throw new RuntimeException("Unable to create OwnedIngredient due to aIngredient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create ownedIngredient due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(int aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public int getAmount()
  {
    return amount;
  }
  /* Code from template association_GetOne */
  public Ingredient getIngredient()
  {
    return ingredient;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setIngredient(Ingredient aNewIngredient)
  {
    boolean wasSet = false;
    if (aNewIngredient != null)
    {
      ingredient = aNewIngredient;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeOwnedIngredient(this);
    }
    user.addOwnedIngredient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ingredient = null;
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeOwnedIngredient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "amount" + ":" + getAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ingredient = "+(getIngredient()!=null?Integer.toHexString(System.identityHashCode(getIngredient())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}