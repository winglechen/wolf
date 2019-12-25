package study.daydayup.wolf.demo.my.starter.ref;

/**
 * study.daydayup.wolf.demo.my.starter.ref
 *
 * @author Wingle
 * @since 2019/12/25 9:10 下午
 **/
public class Obj {
    private Long id = 1L;
    private String name = "abc";
    private Boolean gender = true;

    public void show() throws Exception {
        boolean bool = notNull(id, name, gender);

        System.out.println("state: " + bool);
    }

    private boolean notNull(Object... fields) {
        for (Object o : fields) {
            if (o == null) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        new Obj().show();
    }

}
