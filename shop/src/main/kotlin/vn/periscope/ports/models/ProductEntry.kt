package vn.periscope.ports.models

import vn.periscope.ports.models.audit.CreatedAt
import vn.periscope.ports.models.audit.UpdatedAt
import vn.periscope.ports.models.fieldtype.DoubleField
import vn.periscope.ports.models.fieldtype.LongField
import vn.periscope.ports.models.fieldtype.StringField
import vn.periscope.ports.models.id.LongId
import vn.periscope.share.statics.ProductType

data class ProductEntry(
    val id: LongId,
    val type: ProductType,
    val name: Name,
    val photo: Photo,
    val brand: Brand,
    val industry: Industry,
    val categories: CategoryCollection,
    val price: Price,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt,
) {

    data class Name(
        private val name: String
    ) : StringField(name) {
        companion object {
            fun of(name: String) = Name(name)
        }
    }

    data class Photo(
        private val photoId: Long
    ) : LongField(photoId) {

        companion object {
            fun of(photoId: Long) = Photo(photoId)
        }
    }

    data class Brand(
        private val brandId: Long
    ) : LongField(brandId) {

        companion object {
            fun of(brandId: Long) = Brand(brandId)
        }
    }

    data class Industry(
        private val industryId: Long
    ) : LongField(industryId) {
        companion object {
            fun of(industryId: Long) = Industry(industryId)
        }
    }

    data class Price(
        private val price: Double
    ) : DoubleField(price) {

        companion object {
            fun of(price: Double) = Price(price)
        }
    }

    data class Category(
        private val id: LongId
    )

    data class CategoryCollection(
        private val categories: Set<Category>,
        private val removeCategories: Set<Category>
    ) {
        companion object {

            fun of(categories: Set<Category>) = CategoryCollection(
                categories = categories,
                removeCategories = setOf()
            )
        }
    }
}

