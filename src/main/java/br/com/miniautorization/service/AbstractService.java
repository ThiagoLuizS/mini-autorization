package br.com.miniautorization.service;

import br.com.miniautorization.models.mapper.MapStructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Slf4j
public abstract class AbstractService<T, View, Form> {

    protected abstract JpaRepository<T, Long> getRepository();

    protected abstract MapStructMapper<T, View, Form> getConverter();

    public View save(Form form) {
        log.debug(">> save [form={}] ", form);
        T converting = getConverter().formToEntity(form);
        log.debug(">> save [converting={}] ", converting);
        T t = getRepository().save(converting);
        log.debug("<< save [t={}] ", t);
        View view =  getConverter().entityToView(t);
        log.debug("<< save [view={}] ", view);
        return view;
    }

}
