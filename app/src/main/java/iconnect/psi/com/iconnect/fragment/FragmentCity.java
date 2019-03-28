package iconnect.psi.com.iconnect.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.adapter.CityRecyclerAdapter;
import iconnect.psi.com.iconnect.interfaces.ApiInterface;
import iconnect.psi.com.iconnect.model.CityResponse;
import iconnect.psi.com.iconnect.networkclient.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class FragmentCity extends Dialog implements View.OnClickListener {
        public static String  cityName1;
    private final MyDialogFragmentListener listener;
    private final int position1;

    //private RecyclerView mCityRecyclerView;
    private CityRecyclerAdapter mAdapter;
    private List<CityResponse.Datum> citySelected=new ArrayList<CityResponse.Datum>();
    private List<CityResponse.Datum> cityName=new ArrayList<CityResponse.Datum>();
   private ArrayList<String> listofCity=new ArrayList<>();
    private SearchView searchView;

    private ListView list;
    private ArrayList<String> city;
    private EditText filterText = null;
    ArrayAdapter<String> adapter = null;
    private static final String TAG = "CityList";

    public FragmentCity(@NonNull final Context context, final MyDialogFragmentListener listener, final int position1) {

        super(context);
        this.listener = listener;
        this.position1 = position1;


        setContentView(R.layout.city_dialog);
        this.setTitle("Select City");
        filterText = (EditText) findViewById(R.id.EditBox);
        filterText.addTextChangedListener(filterTextWatcher);
        list=(ListView)findViewById(R.id.List);

        city=new ArrayList<>();
        list = (ListView) findViewById(R.id.List);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, city);
        getCityData();

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                Log.d(TAG, "Selected Item is = "+list.getItemAtPosition(position));

                cityName1=city.get(position).toString().trim();
                listener.onReturnValue(cityName1,position1);
                dismiss();
            }
        });

    }
    public interface MyDialogFragmentListener {
        public void onReturnValue(String foo,int postions);
    }

/*
    @SuppressLint("ValidFragment")
    public FragmentCity(CreateNewTravelRequestActivity createNewTravelRequestActivity) {
    }
*/

/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.city_dialog,container,false);
        mCityRecyclerView = view.findViewById(R.id.cityRecyclerview);
        searchView=view.findViewById(R.id.searchView);
        mAdapter=new CityRecyclerAdapter(this,cityName,this);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCityData();


        return view;


    }
*/

    private void getCityData() {
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("API_KEY", "72729a5129c69fc3b53ddf8d2790a5b0");

        ApiInterface apiInterface= ApiClient.getClientCI().create(ApiInterface.class);
        apiInterface.sendCityResponse(hashMap).enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                Log.e("Sign in Url", "" + call.request().url());

            /*    setAdapter(cityName);*/
            city.clear();

                for (int i=0;i<response.body().getData().size();i++) {

                        String city1=response.body().getData().get(i).getCityName();
                        if(!city.contains(city1)) {
                            city.add(city1);
                        }

                   /* setAdapter(city);*/
                }
              /*  mAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("Login Failed", "" + t.getMessage());
            }
        });
    }

   /* private void setAdapter(List<String> cityName) {
        mAdapter = new CityRecyclerAdapter (getContext(),cityName);
       *//* mCityRecyclerView.setHasFixedSize(true);
        mCityRecyclerView.setAdapter(mAdapter);*//*

        mAdapter.setClickListener(new CityRecyclerAdapter.OnClickListener() {
            @Override
            public boolean onItemClickListener(int position, View view, List<CityResponse.Datum> listcity) {
                Log.e("Selected city",""+listcity.size());
                citySelected=listcity;
                return true;
            }

        });*/
/*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);

                return false;
            }
        });
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public void onCitySelected(CityResponse.Datum cityResponse) {
        Toast.makeText(getActivity(), "_Selected:"+cityResponse.getCityName(), Toast.LENGTH_SHORT).show();

    }
*/

    @Override
    public void onClick(View view) {

    }
    private TextWatcher filterTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapter.getFilter().filter(s);

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onStop() {
        filterText.removeTextChangedListener(filterTextWatcher);
    }
}
