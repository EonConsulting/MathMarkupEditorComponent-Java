/*
 * Copyright (c) 2016, University of South Africa and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package za.co.unisa.observers;

/**
 *
 * @author Mufaro Benedict
 */
import java.util.ArrayList;
import java.util.List;

public class Subject {
/**
 * Observers are going to be stored in an Arraylist observers 
 */	
   private List<Observer> observers = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }
/**
 * setState method changes the state of the subject and notifies all observers
 * @param state an Interger passed to the method is used to trigger a change. 
 */
   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }
/**
 * 
 * @param observer  registers  observers 
 */
   public void attach(Observer observer){
      observers.add(observer);		
   }
   /**
    * 
    * @param observer de registers an observer 
    */
public void dettach(Observer observer){
      observers.remove(observer);		
   }
/**
 * notifies all observers which are registered and updates concerned observers 
 */
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
}
