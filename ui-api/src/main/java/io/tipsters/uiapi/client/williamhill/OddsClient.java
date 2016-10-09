package io.tipsters.uiapi.client.williamhill;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public class OddsClient {
  public interface Uk {
    @GET("/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=1&marketSort=MR&filterBIR=N")
    Call<ResponseBody> matches();
  }

  public interface Europe {
    @GET("/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=46&marketSort=MR&filterBIR=N")
    Call<ResponseBody> matches();
  }

  public interface International {
    @GET("/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=36&marketSort=MR&filterBIR=N")
    Call<ResponseBody> matches();
  }
}
