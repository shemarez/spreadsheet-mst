import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class LinkedListTest
{
  @Test
  public void testIterator()
  {
    LinkedList terms = new LinkedList();
    Literal lit = new Literal(2, 3);
    LinkedList.Iterator iter = terms.zeroth();
    terms.insert(lit, iter);
    iter = terms.iterator();
    assertFalse(terms.isEmpty());
    assertEquals("Check the first item", iter.getElement(), lit);
  }
}
