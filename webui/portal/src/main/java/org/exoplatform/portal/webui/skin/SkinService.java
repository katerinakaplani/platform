/***************************************************************************
 * Copyright 2001-2007 The eXo Platform SARL         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package org.exoplatform.portal.webui.skin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by The eXo Platform SARL
 * Author : Tuan Nguyen
 *          tuan.nguyen@exoplatform.com
 * Jan 19, 2007  
 */
public class SkinService {

  private Map<String, SkinConfig>  skinConfigs_ ;
  private HashSet<String>  availableSkins_ ;

  public SkinService() {
    skinConfigs_ = new  HashMap<String, SkinConfig>(20) ;
    availableSkins_ = new HashSet<String>(5) ;
  }

  /**
   * TODO: should return a collection or list
   * This method should return the availables  skin in the service
   * @return
   */
  public Iterator<String> getAvailableSkins() {
    return  availableSkins_.iterator() ;
  }

  /**
   * 
   * @param module
   * @param skinName
   * @param cssPath
   */
  public void addSkin(String module , String skinName, String cssPath) {
    addSkin(module, skinName, cssPath, false) ;
  }

  public void addSkin(String module , String skinName, String cssPath, boolean isPrimary) {
    availableSkins_.add(skinName);
    String key = module + "$" + skinName ;
    SkinConfig skinConfig = skinConfigs_.get(key);
    if(skinConfig == null || skinConfig.isPrimary() == false) skinConfigs_.put(key, new SkinConfig(module, skinName, cssPath, isPrimary));
  }

  public SkinConfig getSkin(String module, String skinName) {
    String key = module + "$" + skinName ;
    if(skinName.length() == 0)  key = module + "$Default" ;
    SkinConfig config = skinConfigs_.get(key);    
    return config ;    
  }
  
  public int size(){ return skinConfigs_.size(); }

  public SkinConfig getSkin(String key) {return skinConfigs_.get(key); }

  public void remove(String key) throws Exception {
    skinConfigs_.remove(key);
  }

  public void remove(String module, String skinName) throws Exception {
    String key = module + "$" + skinName ;
    if(skinName.length() == 0) key = module + "$Default" ;
    skinConfigs_.remove(key);
  }
}