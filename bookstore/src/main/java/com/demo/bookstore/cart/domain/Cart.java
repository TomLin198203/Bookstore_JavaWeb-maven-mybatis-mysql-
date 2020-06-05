package com.demo.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String,CartItem> map=new HashMap<String,CartItem>();

    public void add(CartItem cartItem){
        if(map.containsKey(cartItem.getBook().getBid())){
            CartItem cartItem1 = map.get(cartItem.getBook().getBid());
            cartItem1.setCount(cartItem.getCount()+cartItem1.getCount());
            map.put(cartItem.getBook().getBid(),cartItem1);
        }else{
            map.put(cartItem.getBook().getBid(),cartItem);
        }
    }

    public void clear(){
        map.clear();
    }

    public void delete(String bid){
        map.remove(bid);
    }

    public Collection<CartItem> getCartItems(){
        return map.values();
    }

    public double getTotal(){
        Collection<CartItem> cartItems = getCartItems();
        BigDecimal t=new BigDecimal("0");
        for (CartItem cartItem : cartItems) {
            t=t.add(new BigDecimal(cartItem.getSubtotal()+"") );
        }
        return t.doubleValue();
    }
}
