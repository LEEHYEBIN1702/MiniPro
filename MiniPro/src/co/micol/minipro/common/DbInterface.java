package co.micol.minipro.common;

import java.util.ArrayList;

public interface DbInterface<T> {
   public ArrayList<T> selectList();
   public T select(T vo);
   public int insert(T vo);
   public int update(T vo);
   public int delete(T vo);
   
}
