package banque.resources;

import com.google.inject.*;
import com.google.inject.*;


public class BanqueModule implements Module
{
   public void configure(final Binder binder)
   {
      binder.bind(banque.resources.BanqueRessource.class);
   }
}
