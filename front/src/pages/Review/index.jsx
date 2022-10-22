import React from "react";

import ReviewList from "./List";
import Map from "../Map";

import { RowFlexWrapper, LeftWrapper, RightWrapper } from "./style";
import { useState } from "react";
import AverageReview from "./AverageReview";

function ReviewPage() {
  const [items, setItems] = useState([]);

  return (
    <RowFlexWrapper>
      <LeftWrapper>
        <AverageReview />
        <ReviewList items={items} />
      </LeftWrapper>
      <RightWrapper>
        <Map setItems={setItems} />
      </RightWrapper>
    </RowFlexWrapper>
  );
}

export default ReviewPage;
