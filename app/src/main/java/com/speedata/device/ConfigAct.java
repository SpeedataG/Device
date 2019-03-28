package com.speedata.device;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.speedata.bean.BaseBean;
import com.speedata.device.adapter.ModuleAdapter;
import com.speedata.device.utils.AlertUtils;
import com.speedata.device.widgt.GPIODeletePop;
import com.speedata.device.widgt.GPIOPop;
import com.speedata.device.widgt.SerialPortPop;
import com.speedata.libutils.CommonUtils;
import com.speedata.libutils.ConfigUtils;
import com.speedata.libutils.ReadBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigAct extends AppCompatActivity implements View.OnClickListener {

    private ReadBean mRead;
    private Button btnSave;

    private ImageView imgFaq;
    private ImageView imgBack;
    private TextView title;

    private List<BaseBean> moduleList = new ArrayList<>();
    private ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        showView();
    }

    private void showView() {
        if (ConfigUtils.isConfigFileExists()) {

            title.setText("V" + CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_content));
        } else {
            title.setText("V" + CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_not_exists));
        }
        mRead = ConfigUtils.readConfig(this);
        setView();
    }

    private void fillList() {
        moduleList.clear();
        moduleList.add(mRead.getId2().setName("id"));
        moduleList.add(mRead.getUhf().setName("UHF"));
        moduleList.add(mRead.getR6().setName("R6"));
        moduleList.add(mRead.getPrint().setName("打印"));
        moduleList.add(mRead.getPasm().setName("Pasm"));
        moduleList.add(mRead.getFinger().setName("指纹"));
        moduleList.add(mRead.getDist().setName("测距"));
        moduleList.add(mRead.getTemp().setName("测温"));
        moduleList.add(mRead.getLf1().setName("LF1"));
        moduleList.add(mRead.getLf2().setName("LF2"));
        moduleList.add(mRead.getSp433().setName("SP433"));
        moduleList.add(mRead.getScan().setName("扫描"));
        moduleList.add(mRead.getZigbee().setName("Zigbee"));
        moduleList.add(mRead.getInfrared().setName("Infrared"));
    }

    private void setView() {
        Toolbar tToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(tToolBar);

        fillList();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        moduleAdapter = new ModuleAdapter(moduleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moduleAdapter);

        moduleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_name:
                        if ("Pasm".equals(Objects.requireNonNull(moduleList.get(position).getName()))) {
                            showResetGpioDialog(mRead.getPasm().getResetGpio());
                        }
                        break;
                    case R.id.tv_serialPort:
                        new SerialPortPop(ConfigAct.this, view, position, SerialPortPop.STYLE.SERIAL_PORT).setOnButtonClickListener(new SerialPortPop.OnButtonClickListener() {
                            @Override
                            public void onClick(SerialPortPop.STYLE style, int position, String content) {
                                moduleList.get(position).setSerialPort(content);
                                moduleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.tv_braut:
                        new SerialPortPop(ConfigAct.this, view, position, SerialPortPop.STYLE.BRAUT).setOnButtonClickListener(new SerialPortPop.OnButtonClickListener() {
                            @Override
                            public void onClick(SerialPortPop.STYLE style, int position, String content) {
                                moduleList.get(position).setBraut(Integer.parseInt(content));
                                moduleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.tv_power_type:
                        new SerialPortPop(ConfigAct.this, view, position, SerialPortPop.STYLE.TYPE).setOnButtonClickListener(new SerialPortPop.OnButtonClickListener() {
                            @Override
                            public void onClick(SerialPortPop.STYLE style, int position, String content) {
                                moduleList.get(position).setPowerType(content);
                                moduleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.tv_add:
                        new GPIOPop(ConfigAct.this, view, position).setOnButtonClickListener(new GPIOPop.OnButtonClickListener() {
                            @Override
                            public void onClick(int position, int content) {
                                moduleList.get(position).getGpio().add(content);
                                moduleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.tv_delete:
                        new GPIODeletePop(ConfigAct.this, view, position, moduleList.get(position).getGpio()).setOnButtonClickListener(new GPIODeletePop.OnButtonClickListener() {
                            @Override
                            public void onClick(int position, int deletePosition) {
                                moduleList.get(position).getGpio().remove(deletePosition);
                                moduleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigUtils.writeConfig(mRead);
                finish();
            }
        });
        title = findViewById(R.id.tv_title);
        imgFaq = findViewById(R.id.img_faq);
        imgBack = findViewById(R.id.img_back);
        imgFaq.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == imgFaq) {
            startActivity(new Intent(this, ConfigFaqActivity.class));
//            Intent intent = new Intent("com.speedata.device");
//            intent.putExtra("URL", "http://www.baidu.com");
//            startActivity(intent);
        } else if (v == imgBack) {
            finish();
        }
    }

    private EditText editText;

    /**
     * resetgpio对话框
     */
    public void showResetGpioDialog(int resetgpio) {
        editText = new EditText(ConfigAct.this);
        editText.setText(String.valueOf(resetgpio));
        AlertUtils.dialog(ConfigAct.this, 0, R.string.resetgpio, editText, (dialog, which) -> {

            String reset = editText.getText().toString();
            if (!AlertUtils.isNumeric(reset)){
                Toast.makeText(ConfigAct.this, getString(R.string.gpio_number), Toast.LENGTH_SHORT).show();
                return;
            }
            mRead.getPasm().setResetGpio(Integer.valueOf(reset));

        });
    }

}
