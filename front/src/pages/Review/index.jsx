import { useState } from "react";
import { Button } from "antd";
import { useMutation } from "@tanstack/react-query";

import ReviewList from "./List";

import { PageWrapper } from "./styled";
import axios from "axios";

function ReviewPage() {
  const [selectedReview, setSelectedReview] = useState({});

  const { mutate } = useMutation(() => {
    axios.post("/reviews/new", {
      space: selectedReview[0],
      parkingLevel: selectedReview[1],
      price: selectedReview[2],
      staff: selectedReview[3],
      revisit: selectedReview[4],
      score: selectedReview[5],
    });
  });

  const handleButtonClick = (e, index) => {
    setSelectedReview((prev) => ({ ...prev, [index]: e.target.value }));
  };

  const handleRateClick = (value) => {
    setSelectedReview((prev) => ({ ...prev, 5: value }));
  };

  const handleSubmitReview = (e) => {
    e.preventDefault();
    if (
      !selectedReview[0] ||
      !selectedReview[1] ||
      !selectedReview[2] ||
      !selectedReview[3] ||
      !selectedReview[4] ||
      !selectedReview[5]
    ) {
      console.log("error");
      return;
    }

    mutate();
  };

  return (
    <PageWrapper>
      <h1>리뷰쓰기</h1>
      <ReviewList onClick={handleButtonClick} onChange={handleRateClick} />
      <Button size="large" onClick={handleSubmitReview}>
        리뷰 남기기
      </Button>
    </PageWrapper>
  );
}

export default ReviewPage;
