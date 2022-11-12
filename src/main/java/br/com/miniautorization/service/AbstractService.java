package br.com.miniautorization.service;

import br.com.miniautorization.models.mapper.MapStructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public abstract class AbstractService<T, View, Form> {

    protected abstract JpaRepository<T, Long> getRepository();

    protected abstract MapStructMapper<T, View, Form> getConverter();

    public View save(Form form) throws Exception {
        try {
            log.info(">> save [form={}] ", form);
            T converting = getConverter().formToEntity(form);
            log.info(">> save [converting={}] ", converting);
            T t = getRepository().save(converting);
            log.info("<< save [t={}] ", t);
            View view =  getConverter().entityToView(t);
            log.info("<< save [view={}] ", view);
            return view;
        } catch (Exception e) {
            log.error("<< save [error={}] ", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

}
