package si.fri.prpo.nakupovalniseznami.interceptorji;

import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.zrno.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object stejKlice(InvocationContext context) throws Exception {
        belezenjeKlicevZrno.klic();
        return context.proceed();
    }
}
