package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author 2106913
 */
public interface TipoItemMapper {
    
    public TipoItem consultarItem(int id);
    
    public void insertarItem(int id, String descripcion);

        
}
