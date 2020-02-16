/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/

package mealPlanner.model;

/**
 * Unable to update umple code due to error at [12,2]
 */
// line 3 "model.ump"
// line 62 "model.ump"
public class DietType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DietType Attributes
  private String type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DietType(String aType)
  {
    type = aType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public String getType()
  {
    return type;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "type" + ":" + getType()+ "]";
  }
}