import React, { useState } from "react";
import { Drawer, Button, Space } from "antd";
import { MenuOutlined } from "@ant-design/icons";

function InfoDrawer() {
  const [visible, setVisible] = useState(false);

  const showDrawer = () => {
    setVisible(true);
  };

  const onClose = () => {
    setVisible(false);
  };

  return (
    <>
      <Space>
        <Button type="text" onClick={showDrawer}>
          <MenuOutlined />
        </Button>
      </Space>
      <Drawer
        title="홍컴님"
        placement="left"
        width={"300px"}
        onClose={onClose}
        visible={visible}
      >
        <p>마이페이지</p>
        <p>작성한 리뷰</p>
      </Drawer>
    </>
  );
}

export default InfoDrawer;
