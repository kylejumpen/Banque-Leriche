package banque;

import com.google.inject.Binder;
import com.google.inject.Module;

public class BanqueModule implements Module
{
   public void configure(final Binder binder)
   {
      binder.bind(banque.BanqueRessource.class);
   }
}
