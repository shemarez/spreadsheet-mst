/**
 * Polynomial class.
 * @author Son Pham
 * @version 1.0
 */

public class Polynomial
{
  //Constant field
  
  /**
   * Initial capacity of the buffer string.
   */
  private static final int BUFFER = 200;
  
  //Instance field
  
  /**
   * The linked list that stores all the terms of the polynomial except zero-terms.
   * Notes that my_terms will store the terms in descending order, ie. higher
   * power terms will be in front of lower power terms. 
   */
  private LinkedList my_terms;
  
  //Constructors
  
  /**
   * Construct an empty polynomial.
   */
  public Polynomial()
  {
    my_terms = new LinkedList();
  }
  
  //Instance methods
  
  // @ requires !this.my_terms.isEmpty(); 
  /**
   * @param coefficient The coefficient.
   * @param exponent The exponent.
   * @return The product of multiplying this polynomial with the input term.
   */
  private Polynomial timesOneTerm(final int coefficient, final int exponent)
  {
    final Polynomial result = new Polynomial();
    final LinkedList result_terms = result.my_terms;
    final LinkedList.Iterator result_iter = result_terms.zeroth();
    final LinkedList.Iterator iter = my_terms.iterator();
    Literal lit;
    Literal new_lit;
    while (iter.hasNext())
    {
      lit = (Literal) iter.next();
      new_lit = new Literal(lit.getCoefficient() * coefficient,
                            lit.getExponent() + exponent);
      result_terms.insert(new_lit, result_iter);
      result_iter.next();
    }
    return result;
  }
  
  private LinkedList getTerms()
  {
    return my_terms;
  }
  
  private void setTerms(final LinkedList the_terms)
  {
    my_terms = the_terms;
  }
  
  // @ ensures: new term will be inserted in the correct place in my_terms.
  /**
   * Insert a new term to the polynomial.
   * @param coefficient The coefficient of the new term.
   * @param exponent The exponent of the new term.
   */
  public void insertTerm(final int coefficient, final int exponent)
  {
    final Literal term = new Literal(coefficient, exponent);
    final LinkedList.Iterator iter = my_terms.zeroth();
    
    if (coefficient != 0) //if the new term is not equal to 0
    {
      if (my_terms.isEmpty())
      {
        my_terms.insert(term, iter);
      }
      else
      {
        // iter_2 points to the previous node respective to iter
        final LinkedList.Iterator iter_2 = my_terms.zeroth();
        
        iter.next();        
        Literal temp_lit;
        
        do
        {
          temp_lit = (Literal) iter.next();
         
          if (temp_lit.getExponent() > exponent)
          {
            iter_2.next();
          }
          else if (temp_lit.getExponent() == exponent)
          {
            temp_lit.setCoefficient(temp_lit.getCoefficient() + coefficient);
            if (temp_lit.getCoefficient() == 0)
            {
              my_terms.remove(iter_2);
            }
            return;
          }
          else
          {
            my_terms.insert(term, iter_2);
            return;
          }
        } while (iter.hasNext());
  
        my_terms.insert(term, iter_2);
      }    
    }
  }
  
  /**
   * Set the polynomial to 0.
   */
  public void zeroPolynomial()
  {
    my_terms.makeEmpty();
  }
  
  /**
   * @return The polynomial times -1
   */
  public Polynomial negate()
  {
    final Polynomial result = new Polynomial();
    
    if (!my_terms.isEmpty())
    {
      final LinkedList result_terms = result.my_terms;
      final LinkedList.Iterator result_iter = result_terms.zeroth();
      final LinkedList.Iterator iter = my_terms.iterator();
      while (iter.hasNext())
      {
        final Literal lit = (Literal) iter.next();
        final Literal new_lit = 
          new Literal(lit.getCoefficient() * -1, lit.getExponent());
        result_terms.insert(new_lit, result_iter);
        result_iter.next();
      }
    }
    
    return result;
  }
  
  /**
   * @param polynomial The polynomial.
   * @return The result of adding the input polynomial to the this polynomial.
   */
  public Polynomial plus(final Polynomial polynomial)
  {
    final Polynomial result = new Polynomial();
    final LinkedList result_terms = result.getTerms();
    final LinkedList.Iterator the_other_iter = polynomial.my_terms.iterator();
    final LinkedList.Iterator iter = my_terms.iterator();    
    final LinkedList.Iterator result_iter = result_terms.zeroth();
    Literal this_lit;
    Literal the_other_lit;    

    // Iterate through the linked list terms of the two polynomials and 
    // insert each term into the linked list terms of the result polynomial
    // in descending order. Stop when either linked list terms is empty.
    while (iter.hasNext() && the_other_iter.hasNext())
    {
      this_lit = (Literal) iter.getElement();
      the_other_lit = (Literal) the_other_iter.getElement();
      
      if (this_lit.getExponent() > the_other_lit.getExponent())
      {
        result_terms.insert(this_lit, result_iter);
        iter.next();
        result_iter.next();
      }
      else if (this_lit.getExponent() == the_other_lit.getExponent())
      {
        final Literal temp_lit =
            new Literal(the_other_lit.getCoefficient() + this_lit.getCoefficient(), 
                        this_lit.getExponent());
        if (temp_lit.getCoefficient() != 0)
        {
          result_terms.insert(temp_lit, result_iter);
          result_iter.next();
        }
        iter.next();
        the_other_iter.next();
      }
      else   // this_lit.getExponent() < the_other_lit.getExponent();
      {
        result_terms.insert(the_other_lit, result_iter);
        the_other_iter.next();
        result_iter.next();
      }
      
    }

    // Iterate through the rest of the linked list terms of this polynomial and
    // insert the terms into the linked list terms of the result polynomial.
    while (iter.hasNext())
    {
      //this_lit = (Literal) iter.getElement();
      this_lit = (Literal) iter.next();
      result_terms.insert(this_lit, result_iter);
      //iter.next();
      result_iter.next();
    }

    // Iterate through the rest of the linked list terms of the other polynomial and
    // insert the terms into the linked list terms of the result polynomial.
    while (the_other_iter.hasNext())
    {
      //the_other_lit = (Literal) the_other_iter.getElement();
      the_other_lit = (Literal) the_other_iter.next();
      result_terms.insert(the_other_lit, result_iter);
      //the_other_iter.next();
      result_iter.next();
    }

    return result;
  }
  
  /**
   * @param polynomial The polynomial.
   * @return The result of subtracting this polynomial by the input polynomial.
   * @throws CloneNotSupportedException 
   */
  public Polynomial minus(final Polynomial polynomial)
  {
    final Polynomial negate_polynomial = polynomial.negate();
    return this.plus(negate_polynomial);
  }
  
  /**
   * @param polynomial The polynomial.
   * @return The result of multiplying this polynomial and the input polynomial.
   */
  public Polynomial times(final Polynomial polynomial)
  {
    Polynomial result = new Polynomial();
    if (!my_terms.isEmpty() && !polynomial.my_terms.isEmpty())
    {
      final LinkedList.Iterator iter = my_terms.iterator();
      Literal lit;
      Polynomial partial_product;
      while (iter.hasNext())
      {
        lit = (Literal) iter.next();
        partial_product = polynomial.timesOneTerm(lit.getCoefficient(), lit.getExponent());
        result = result.plus(partial_product);
      }
    }
    return result;
  }
  
  /**
   * @return The derivative of this polynomial.
   */
  public Polynomial derivative()
  {
    final Polynomial result = new Polynomial();
    if (!my_terms.isEmpty())
    {      
      final LinkedList result_terms = result.my_terms;
      final LinkedList.Iterator result_iter = result_terms.zeroth();
      final LinkedList.Iterator iter = my_terms.iterator();
      
      while (iter.hasNext())
      {
        final Literal lit = (Literal) iter.next();        
        final int new_coeff = lit.getCoefficient() * lit.getExponent();
        final int new_expo = lit.getExponent() - 1;
        final Literal new_lit = new Literal(new_coeff, new_expo);
        if (new_coeff != 0)
        {
          result_terms.insert(new_lit, result_iter);
          result_iter.next();
        }
      }
    }
    return result;
  }
  
  private String printExponent(final int expo)
  {
    String result = "";
    if (expo > 0)
    {
      if (expo == 1)
      {
        result = "x";
      }
      else
      {
        result = "x^" + expo;
      }
    }
    else if (expo < 0)
    {
      result = "x^(" + expo + ")";
    }
    return result;
  }
  
  private String printTerm(final int coeff, final int expo)
  {
    String result = null;
    if (coeff > 0)
    {
      if (coeff == 1)
      {
        if (expo != 0)
        {
          result = "+" + printExponent(expo);
        }
        else
        {
          result = "+1";
        }
      }
      else
      {
        result = "+" + coeff + printExponent(expo);
      }
    }
    else if (coeff < 0)
    {
      if (coeff == -1)
      {
        if (expo != 0)
        {
          result = "-" + printExponent(expo);
        }
        else
        {
          result = "-1";
        }
      }
      else
      {
        result = coeff + printExponent(expo);
      }
    }
    return result;
  }
  
  /**
   * @return The string representation of this polynomial.
   */
  public String print()
  {    
    if (!my_terms.isEmpty())
    {
      final LinkedList.Iterator iter = my_terms.iterator();
      final StringBuffer buff = new StringBuffer(BUFFER);
      Literal lit = (Literal) iter.getElement();
      iter.next();
      //if (iter.hasNext())
      //{
        if (lit.getCoefficient() > 0)
        {
          if (lit.getCoefficient() != 1)
          {
            buff.append(lit.getCoefficient());            
          }
          buff.append(printExponent(lit.getExponent()));
        }
        else
        {
          if (lit.getCoefficient() != -1)
          {
            buff.append(lit.getCoefficient());
          }
          else
          {
            buff.append("-");
          }
          buff.append(printExponent(lit.getExponent()));
        }
        //buff.append(printTerm(lit.getCoefficient(), lit.getExponent()));
        while (iter.hasNext())
        {
          lit = (Literal) iter.next();
          buff.append(printTerm(lit.getCoefficient(), lit.getExponent()));
          //buff.append(printExponent(lit.getExponent()));
          //lit = (Literal) iter.next();
        } 
      /*}
      else  // There is only one term in the polynomial
      {
        if (lit.getCoefficient() > 0)
        {
          if (lit.getCoefficient() != 1)
          {
            buff.append(lit.getCoefficient());            
          }
          buff.append(printExponent(lit.getExponent()));
        }
        else
        {
          if (lit.getCoefficient() != -1)
          {
            buff.append(lit.getCoefficient());
          }
          else
          {
            buff.append("-");
          }
          buff.append(printExponent(lit.getExponent()));
        }
      }*/
      return buff.toString();
    }
    else
    {
      return "0";
    }
    /*
    if (!my_terms.isEmpty())
    {
      final LinkedList.Iterator iter = my_terms.iterator();
      final StringBuffer buff = new StringBuffer(BUFFER);
      Literal lit = (Literal) iter.getElement();
      if (lit.getCoefficient() != 0)
      {
        if (lit.getExponent() == 0)
        {
          buff.append(lit.getCoefficient());
        }
        else  // lit.getExponent() != 0
        {
          if (lit.getCoefficient() == -1)
          {
            buff.append("-");
          }
          else if (lit.getCoefficient() != 1)
          {
            buff.append(lit.getCoefficient());
          }
          
          if (lit.getExponent() < 0)
          {
            buff.append("x^(" + lit.getExponent() + ")");
          }
          else if (lit.getExponent() == 1)
          {
            buff.append("x");            
          }
          else
          {
            buff.append("x^" + lit.getExponent());
          }
        }
        iter.next();
      }
      else 
      {
        iter.next();
        if (iter.hasNext())
        {
          buff.append("0");
        }
      }      
      while (iter.hasNext())
      {        
        lit = (Literal) iter.getElement();
        iter.next();
        
        if (lit.getCoefficient() != 0)
        {
          if (lit.getExponent() == 0)
          {
            if (lit.getCoefficient() > 0)
            {
              buff.append(" +" + lit.getCoefficient());
            }
            else 
            {
              buff.append(" " + lit.getCoefficient());
            }
          }
          else  // lit.getExponent() != 0
          {
            if (lit.getCoefficient() > 1)
            {
              buff.append(" +" + lit.getCoefficient());
            }
            else if (lit.getCoefficient() == -1)
            {
              buff.append(" -");
            }
            else if (lit.getCoefficient() == 1)
            {
              buff.append(" +");
            }
            else
            {
              buff.append(lit.getCoefficient());
            }
            
            if (lit.getExponent() < 0)
            {
              buff.append("x^(" + lit.getExponent() + ")");
            }
            else if (lit.getExponent() == 1)
            {
              buff.append("x");              
            }
            else
            {
              buff.append("x^" + lit.getExponent());
            }
          }
        }               
      } 
      return buff.toString();
    }
    else
    {
      return "0";
    }*/
  }
}
