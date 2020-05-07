# CustomLoggingAnnotation
Custom Annotation used for logging by using Spring AOP

Based on the execution strategy of advices, they are of following types.
Before Advice: These advices runs before the execution of join point methods. We can use @Before annotation to mark an advice type as Before advice.
After (finally) Advice: An advice that gets executed after the join point method finishes executing, whether normally or by throwing an exception. We can create after advice using @After annotation.
After Returning Advice: Sometimes we want advice methods to execute only if the join point method executes normally. We can use @AfterReturning annotation to mark a method as after returning advice.
After Throwing Advice: This advice gets executed only when join point method throws exception, we can use it to rollback the transaction declaratively. We use @AfterThrowing annotation for this type of advice.
Around Advice: This is the most important and powerful advice. This advice surrounds the join point method and we can also choose whether to execute the join point method or not. We can write advice code that gets executed before and after the execution of the join point method. It is the responsibility of around advice to invoke the join point method and return values if the method is returning something. We use @Around annotation to create around advice methods.

AOP Key Terms
Let us now define some central AOP concepts and terminology and relate with above example.
1) Aspect : a modularization of a concern that cuts across multiple classes. Transaction management is a good example of a crosscutting concern in enterprise Java applications.

In our example, we have created a logging aspect. To create an aspect, you need to apply @Aspect annotation on your aspect.
example classes are,  class LoggingAspect and LoggingErrorAspect

2) Join point : a point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution.

In our example, methods annotated with @LoggingEvent annotation, defined inside LoggingController are joint points.

3) Advice : action taken by an aspect at a particular join point. Different types of advice include “around,” “before” and “after” advice. Many AOP frameworks, including Spring, model an advice as an interceptor, maintaining a chain of interceptors around the join point.

In our example, all @AfterReturning() and @AfterThrowing()  are advices .

4) Pointcut : a predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut (for example, the execution of a method with a certain name or custom annotation annotation on method). The concept of join points as matched by pointcut expressions is central to AOP, and Spring uses the AspectJ pointcut expression language by default.

In our example, the expressions passed in @AfterReturning(pointcut = "@annotation(LoggingEvent)") and @AfterThrowing(pointcut = "@annotation(LoggingEvent)")   are pointcuts.

5) Target object : object being advised by one or more aspects. Also referred to as the advised object. Since Spring AOP is implemented using runtime proxies, this object will always be a proxied object.

In our example, LoggingController is advised object hence it is the target object.

6) AOP proxy : an object created by the AOP framework in order to implement the aspect contracts (advice method executions and so on). In the Spring Framework, an AOP proxy will be a JDK dynamic proxy or a CGLIB proxy.
