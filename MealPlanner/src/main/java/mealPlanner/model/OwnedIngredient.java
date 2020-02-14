package mealPlanner.model;
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
    boolean didAddIngredient = setIngredient(aIngredient);
    if (!didAddIngredient)
    {
      throw new RuntimeException("Unable to create ownedIngredient due to ingredient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_SetOneToMany */
  public boolean setIngredient(Ingredient aIngredient)
  {
    boolean wasSet = false;
    if (aIngredient == null)
    {
      return wasSet;
    }

    Ingredient existingIngredient = ingredient;
    ingredient = aIngredient;
    if (existingIngredient != null && !existingIngredient.equals(aIngredient))
    {
      existingIngredient.removeOwnedIngredient(this);
    }
    ingredient.addOwnedIngredient(this);
    wasSet = true;
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
    Ingredient placeholderIngredient = ingredient;
    this.ingredient = null;
    if(placeholderIngredient != null)
    {
      placeholderIngredient.removeOwnedIngredient(this);
    }
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