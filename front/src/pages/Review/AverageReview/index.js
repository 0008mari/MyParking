import React from "react";
import axios from "axios";
import { useQuery } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom";
import { Button } from "antd";

import { RowFlexWrapper } from "./style";

function AverageReview() {
  const { parkingId } = useParams();
  const navigate = useNavigate();
  const { data, isLoading } = useQuery(["review", parkingId], async () => {
    const { data } = await axios.get(`/parkings/${parkingId}`);
    return data;
  });

  if (isLoading) return <div>로딩중</div>;

  // empty state
  if (!data) return <div>비었음</div>;

  return (
    <RowFlexWrapper>
      <div>
        <h2>{data.name}</h2>
        <div>별점 {data.reviewStarAvg}</div>
        <div>{data.address}</div>
      </div>
      <div>
        <Button type="primary" size="large" onClick={() => navigate("new")}>
          리뷰 남기기
        </Button>
      </div>
    </RowFlexWrapper>
  );
}

export default AverageReview;
