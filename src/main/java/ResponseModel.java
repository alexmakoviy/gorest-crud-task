public class ResponseModel
{
    private String code;

    private UserResponseModel data;

    private String meta;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public UserResponseModel getUserResponse ()
    {
        return data;
    }

    public void setData (UserResponseModel data)
    {
        this.data = data;
    }

    public String getMeta ()
{
    return meta;
}

    public void setMeta (String meta)
    {
        this.meta = meta;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", data = "+data+", meta = "+meta+"]";
    }
}