package ds2.oss.core.base.impl;

import ds2.oss.core.api.LocaleSupport;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The impl.
 * @author dstrauss
 * @version 0.3
 */
public class LocaleSupportImpl implements LocaleSupport {
  private String baseName;

  public LocaleSupportImpl(String bn){
    this.baseName=bn;
  }

  @Override
  public String resolve(String key, Locale loc, Object... params) {
    ResourceBundle.Control ctrl=null;
    ResourceBundle rb=ResourceBundle.getBundle(baseName,loc,getClass().getClassLoader(), ctrl);
    String pattern=rb.getString(key);
    if(params==null||params.length<=0){
      return pattern;
    }
    MessageFormat mf2=new MessageFormat(pattern, loc);
    String rc=mf2.format(params);
    return rc;
  }

  @Override
  public String formatCurrency(Locale locale, double val) {
    NumberFormat nf=NumberFormat.getCurrencyInstance(locale);
    return nf.format(val);
  }
}
